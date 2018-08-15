package com.magorasystems.pmtoolpush.screen.authorize

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.magorasystems.pmtoolpush.util.fragment.BaseFragment
import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.util.FieldTextWatcher
import com.mgrsys.authorization.authorize.util.ViewUtils
import io.reactivex.disposables.Disposable
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Error as error
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Loading as loading
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Success as success
import kotlinx.android.synthetic.main.fragment_change_pass.change_pass_button as changePassButton
import kotlinx.android.synthetic.main.fragment_change_pass.edit_input_new_password as editNewPass
import kotlinx.android.synthetic.main.fragment_change_pass.edit_input_old_password as editOldPass
import kotlinx.android.synthetic.main.fragment_change_pass.edit_input_repeat_new_password as editRepeatNewPass
import kotlinx.android.synthetic.main.fragment_change_pass.progress as progressView
import kotlinx.android.synthetic.main.fragment_change_pass.text_input_new_password as inputNewPass
import kotlinx.android.synthetic.main.fragment_change_pass.text_input_old_password as inputOldPass
import kotlinx.android.synthetic.main.fragment_change_pass.text_input_repeat_new_password as inputRepeatNewPass

/**
 * Developed 2018.
 *
 * @author mihaylov
 */

class ChangePassFragment : BaseFragment() {

    private lateinit var viewModel: ChangePassViewModel

    companion object {
        fun newInstance(): ChangePassFragment {
            val fragment = ChangePassFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    var viewDisposable: Disposable? = null


    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_change_pass
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChangePassViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()

        changePassButton.setOnClickListener {
            viewModel.changePass(
                    inputOldPass.editText?.text.toString(),
                    inputNewPass.editText?.text.toString(),
                    inputRepeatNewPass.editText?.text.toString()
            )
        }


        viewModel.authorizeSuccess.observe(this, Observer {
            when (it) {
                is loading -> setProgressViewEnabled(true)
                is success -> setProgressViewEnabled(false)
                is error -> {
                    setProgressViewEnabled(false)
                    showMessage(it.error)
                }
            }
        })
        viewModel.oldPasswordError.observe(this, Observer { it?.let { setOldPasswordValidationError(it) } })
        viewModel.newPasswordError.observe(this, Observer { it?.let { setNewPasswordValidationError(it) } })
        viewModel.repeatNewPasswordError.observe(this, Observer { it?.let { setRepeatNewPasswordValidationError(it) } })

    }

    private fun initViews() {
        editOldPass.addTextChangedListener(object : FieldTextWatcher() {
            override fun afterTextChanged(s: String) {
                setOldPasswordValidationError(null)
            }
        })
        editNewPass.addTextChangedListener(object : FieldTextWatcher() {
            override fun afterTextChanged(s: String) {
                setNewPasswordValidationError(null)
            }
        })
        editRepeatNewPass.addTextChangedListener(object : FieldTextWatcher() {
            override fun afterTextChanged(s: String) {
                setRepeatNewPasswordValidationError(null)
            }
        })
    }

    fun setOldPasswordValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputOldPass, message)
    }

    fun setNewPasswordValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputNewPass, message)
    }

    fun setRepeatNewPasswordValidationError(message: String?) {
        ViewUtils.setInputLayoutError(inputRepeatNewPass, message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }


    fun setProgressViewEnabled(enabled: Boolean) {
        changePassButton.isEnabled = !enabled
        progressView.visibility = if (enabled) View.VISIBLE else View.GONE
    }
}