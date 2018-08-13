package com.mgrsys.authorization.authorize.screen.signup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magorasystems.pmtoolpush.screen.authorize.SignInFragment
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
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
import kotlinx.android.synthetic.main.fragment_sign_up.button_sign_up as signUpButton
import kotlinx.android.synthetic.main.fragment_sign_up.progress_layout as progressView
import kotlinx.android.synthetic.main.fragment_sign_up.text_input_mail as inputEmail
import kotlinx.android.synthetic.main.fragment_sign_up.text_input_name as inputName
import kotlinx.android.synthetic.main.fragment_sign_up.text_input_password as inputPassword

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignUpFragment : BaseFragment() {

    private lateinit var viewModel: SignUpViewModel

    companion object {
        fun newInstance(): SignUpFragment {
            val fragment = SignUpFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    var viewDisposable: Disposable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_sign_up
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fieldsValidation()
        signUpButton.setOnClickListener {
            viewModel.signUp(
                    inputEmail.editText?.text.toString(),
                    inputPassword.editText?.text.toString(),
                    inputName.editText?.text.toString()
            )
        }

        viewModel.signUpSuccess.observe(this, Observer {
            when (it) {
                is ViewObject.Loading -> setProgressViewEnabled(true)
                is ViewObject.Success -> setProgressViewEnabled(false)
                is ViewObject.Error -> {
                    setProgressViewEnabled(false)
                    ErrorHandler.handleError(it.error!!, this@SignUpFragment)
                }
            }
        })

    }

    private fun fieldsValidation() {
        val userNameObservable: Observable<Boolean> = RxValidator(inputName)
                .apply {
                    add(EmptyValidatorRule())
                }.asObservable()

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

        viewDisposable = RxCombineValidator(userNameObservable, loginObservable, passwordObservable)
                .asObservable()
                .distinctUntilChanged()
                .subscribe { valid -> signUpButton.isEnabled = valid }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }


    fun setProgressViewEnabled(enabled: Boolean) {
        signUpButton.isEnabled = !enabled
        progressView.visibility = if (enabled) View.VISIBLE else View.GONE
        inputEmail.isEnabled = !enabled
        inputPassword.isEnabled = !enabled
    }
}