package com.magorasystems.pmtoolpush.screen.authorize

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.magorasystems.pmtoolpush.screen.viewobject.auth.CredentialsVo
import com.magorasystems.pmtoolpush.util.fragment.BaseFragment
import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.application.manager.ErrorHandler
import com.mgrsys.authorization.authorize.screen.signin.SignInContract
import com.mgrsys.authorization.authorize.util.FieldTextWatcher
import com.mgrsys.authorization.authorize.util.ViewUtils
import io.reactivex.disposables.Disposable
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Error as error
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Loading as loading
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Success as success
import kotlinx.android.synthetic.main.fragment_sign_in.button_sign_in as authorizeButton
import kotlinx.android.synthetic.main.fragment_sign_in.button_sign_up as signUpButton
import kotlinx.android.synthetic.main.fragment_sign_in.edit_input_mail as editEmail
import kotlinx.android.synthetic.main.fragment_sign_in.edit_input_password as editPassword
import kotlinx.android.synthetic.main.fragment_sign_in.progress_layout as progressView
import kotlinx.android.synthetic.main.fragment_sign_in.text_input_mail as inputEmail
import kotlinx.android.synthetic.main.fragment_sign_in.text_input_password as inputPassword

/**
 * Developed 2018.
 *
 * @author mihaylov
 */

class SignInFragment : BaseFragment(), SignInContract.View {

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


    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_sign_in
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        authorizeButton.setOnClickListener {
            viewModel.authorize(
                    CredentialsVo(
                            inputEmail.editText?.text.toString(),
                            inputPassword.editText?.text.toString()
                    )
            )
        }

        signUpButton.setOnClickListener {
            viewModel.routeToSignUp()
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
        viewModel.loginError.observe(this, Observer { it?.let { setEmailValidationError(it) } })
        viewModel.passwordError.observe(this, Observer { it?.let { setPasswordValidationError(it) } })

    }

    private fun initViews() {
        editEmail.addTextChangedListener(object : FieldTextWatcher() {
            override fun afterTextChanged(s: String) {
                setEmailValidationError(null)
            }
        })
        editPassword.addTextChangedListener(object : FieldTextWatcher() {
            override fun afterTextChanged(s: String) {
                setPasswordValidationError(null)
            }
        })
    }

    override fun setPasswordValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputPassword, message)
    }

    override fun setEmailValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputEmail, message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }


    override fun setProgressViewEnabled(enabled: Boolean) {
        authorizeButton.isEnabled = !enabled
        progressView.visibility = if (enabled) View.VISIBLE else View.GONE
        inputEmail.isEnabled = !enabled
        inputPassword.isEnabled = !enabled
    }
}