package com.mgrsys.authorization.authorize.screen.signup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.util.fragment.BaseFragment
import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.application.manager.ErrorHandler
import com.mgrsys.authorization.authorize.util.FieldTextWatcher
import com.mgrsys.authorization.authorize.util.ViewUtils
import io.reactivex.disposables.Disposable
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Error as error
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Loading as loading
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Success as success
import kotlinx.android.synthetic.main.fragment_sign_up.button_sign_up as signUpButton
import kotlinx.android.synthetic.main.fragment_sign_up.edit_input_mail as editEmail
import kotlinx.android.synthetic.main.fragment_sign_up.edit_input_name as editName
import kotlinx.android.synthetic.main.fragment_sign_up.edit_input_password as editPassword
import kotlinx.android.synthetic.main.fragment_sign_up.progress_layout as progressView
import kotlinx.android.synthetic.main.fragment_sign_up.text_input_mail as inputEmail
import kotlinx.android.synthetic.main.fragment_sign_up.text_input_name as inputName
import kotlinx.android.synthetic.main.fragment_sign_up.text_input_password as inputPassword

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignUpFragment : BaseFragment(), SignUpContract.View {

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


    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_sign_up
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
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
        viewModel.loginError.observe(this, Observer { it?.let { setEmailValidationError(it) } })
        viewModel.passwordError.observe(this, Observer { it?.let { setPasswordValidationError(it) } })
        viewModel.nameError.observe(this, Observer { it?.let { setNameValidationError(it) } })
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
        editName.addTextChangedListener(object : FieldTextWatcher() {
            override fun afterTextChanged(s: String) {
                setNameValidationError(null)
            }
        })
    }

    override fun setPasswordValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputPassword, message)
    }

    override fun setEmailValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputEmail, message)
    }

    override fun setNameValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputName, message)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }


    override fun setProgressViewEnabled(enabled: Boolean) {
        signUpButton.isEnabled = !enabled
        progressView.visibility = if (enabled) View.VISIBLE else View.GONE
        inputEmail.isEnabled = !enabled
        inputPassword.isEnabled = !enabled
    }
}