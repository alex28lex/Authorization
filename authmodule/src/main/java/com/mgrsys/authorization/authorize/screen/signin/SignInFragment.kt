package com.magorasystems.pmtoolpush.screen.authorize

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magorasystems.pmtoolpush.screen.viewobject.auth.CredentialsVo
import com.magorasystems.pmtoolpush.util.fragment.BaseFragment
import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.application.manager.ErrorHandler
import com.mgrsys.authorization.authorize.model.validator.PasswordValidatorRule
import com.mgrsys.blankproject.model.validator.EmailValidateRule
import com.mgrsys.blankproject.model.validator.EmptyValidatorRule
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import ru.whalemare.rxvalidator.RxCombineValidator
import ru.whalemare.rxvalidator.RxValidator
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Error as error
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Loading as loading
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Success as success
import kotlinx.android.synthetic.main.fragment_authorize.button_sign_in as authorizeButton
import kotlinx.android.synthetic.main.fragment_authorize.progress_layout as progressView
import kotlinx.android.synthetic.main.fragment_authorize.text_input_mail as inputEmail
import kotlinx.android.synthetic.main.fragment_authorize.text_input_password as inputPassword
import kotlinx.android.synthetic.main.fragment_authorize.progress_layout as progressView

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */

class SignInFragment : BaseFragment() {

    private lateinit var viewModel: SignInViewModel

    companion object {
        fun newInstance(): SignInFragment {
            val fragment = SignInFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    var viewDisposable: Disposable? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authorize, container, false)
    }

    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_authorize
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val loginObservable: Observable<Boolean> = RxValidator(inputEmail)
                .apply {
                    add(EmptyValidatorRule())
                    add(EmailValidateRule())
                }.asObservable()

        val passwordObservable: Observable<Boolean> = RxValidator(inputPassword)
                .apply {
                    add(EmptyValidatorRule())
                    add(PasswordValidatorRule())
                }.asObservable()

        viewDisposable = RxCombineValidator(loginObservable, passwordObservable)
                .asObservable()
                .distinctUntilChanged()
                .subscribe { valid -> authorizeButton.isEnabled = valid }

        authorizeButton.setOnClickListener {
            viewModel.authorize(
                    CredentialsVo(
                            inputEmail.editText?.text.toString(),
                            inputPassword.editText?.text.toString()
                    )
            )
        }

        viewModel.authorizeSuccess.observe(this, Observer {
            when (it) {
                is loading -> setProgressViewEnabled(true)
                is success -> setProgressViewEnabled(false)
                is error -> {
                    setProgressViewEnabled(false)
                    ErrorHandler.handleError(it.error!!, this@SignInFragment)
                }
            }
        })
        viewModel.navigateToMain.observe(this, Observer {
            //route
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }


    fun setProgressViewEnabled(enabled: Boolean) {
        authorizeButton.isEnabled = !enabled
        progressView.visibility = if (enabled) View.VISIBLE else View.GONE
        inputEmail.isEnabled = !enabled
        inputPassword.isEnabled = !enabled
    }
}