package com.magorasystems.pmtoolpush.screen.authorize


import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.application.manager.ResExtractor
import com.mgrsys.authorization.authorize.model.validator.PasswordValidatorRule
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.usecase.ChangePassUseCase
import com.mgrsys.blankproject.model.validator.EmptyValidatorRule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.whalemare.rxvalidator.Validator
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

/**
 * Developed 2018.
 *
 * @author mihaylov
 */
class ChangePassViewModel : ViewModel() {
    @Inject
    lateinit var changePassUseCase: ChangePassUseCase

    @Inject
    lateinit var router: Router

    private val _changePassSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _changePassSuccess

    private val _newPasswordError = MutableLiveData<String>()
    val newPasswordError: LiveData<String>
        get() = _newPasswordError

    private val _repeatNewPasswordError = MutableLiveData<String>()
    val repeatNewPasswordError: LiveData<String>
        get() = _repeatNewPasswordError

    private val _oldPasswordError = MutableLiveData<String>()
    val oldPasswordError: LiveData<String>
        get() = _oldPasswordError

    private var _authorizeDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }


    fun changePass(oldPass: String, newPass: String, repeatNewPass: String) {
        if (validate(oldPass, newPass, repeatNewPass)) {
            _authorizeDisposable?.dispose()

            _changePassSuccess.value = ViewObject.Loading()
            _authorizeDisposable = changePassUseCase.changePass(oldPass, newPass)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { _changePassSuccess.value = ViewObject.Success(Unit) },
                            { _changePassSuccess.value = ViewObject.Error(it) },
                            { router.exit() }
                    )
        }
    }

    fun validate(oldPass: String, newPass: String, repeatNewPass: String): Boolean {
        val isValid = validatePassword(_newPasswordError, newPass) and validatePassword(_oldPasswordError, oldPass)
        val isMatch: Boolean

        if (isValid && newPass != repeatNewPass) {
            isMatch = false
            _repeatNewPasswordError.setValue(ResExtractor.instance.getString(R.string.change_pass_passwords_matching_error))
        } else
            isMatch = true

        return isMatch && isValid
    }

    fun validatePassword(passFiledValidationError: MutableLiveData<String>, password: String): Boolean {
        val isValid = AtomicBoolean(false)
        val passwordValidatorsComposer = Validator()
        passwordValidatorsComposer.add(EmptyValidatorRule())
        passwordValidatorsComposer.add(PasswordValidatorRule())

        passwordValidatorsComposer.validate(
                password,
                onSuccess = { isValid.set(true) },
                onError = { s: String -> passFiledValidationError.value = s }
        )

        return isValid.get()
    }


    override fun onCleared() {
        super.onCleared()
        _authorizeDisposable?.dispose()
    }
}