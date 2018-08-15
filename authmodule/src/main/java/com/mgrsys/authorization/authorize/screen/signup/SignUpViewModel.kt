package com.mgrsys.authorization.authorize.screen.signup

import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.util.livedata.SingleLiveEvent
import com.mgrsys.authorization.authorize.application.manager.ErrorHandler
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import com.mgrsys.authorization.authorize.model.validator.PasswordValidatorRule
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.usecase.SignUpUseCase
import com.mgrsys.blankproject.model.validator.EmailValidateRule
import com.mgrsys.blankproject.model.validator.EmptyValidatorRule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.whalemare.rxvalidator.Validator
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignUpViewModel : ViewModel() {
    @Inject
    lateinit var signUpUseCase: SignUpUseCase

    @Inject
    lateinit var router: Router

    private val _signUpSuccess = SingleLiveEvent<ViewObject<Unit>>()
    val signUpSuccess: LiveData<ViewObject<Unit>>
        get() = _signUpSuccess

    private val _passwordError = SingleLiveEvent<String>()
    val passwordError: LiveData<String>
        get() = _passwordError

    private val _loginError = SingleLiveEvent<String>()
    val loginError: LiveData<String>
        get() = _loginError

    private val _nameError = SingleLiveEvent<String>()
    val nameError: LiveData<String>
        get() = _nameError

    private var _signUpDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    fun signUp(login: String, password: String, userName: String) {
        if (validateName(userName) and validateLogin(login) and validatePassword(password)) {
            _signUpDisposable?.dispose()

            _signUpSuccess.value = ViewObject.Loading()
            _signUpDisposable = signUpUseCase.signUp(login, password, RegistrationData(userName))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { _signUpSuccess.value = ViewObject.Success(Unit) },
                            { _signUpSuccess.value = ViewObject.Error(ErrorHandler.parseError(it)) },
                            { router.navigateTo(Screens.MAIN) })
        }
    }


    fun validatePassword(password: String): Boolean {
        val isValid = AtomicBoolean(false)
        val passwordValidatorsComposer = Validator()
        passwordValidatorsComposer.add(EmptyValidatorRule())
        passwordValidatorsComposer.add(PasswordValidatorRule())

        passwordValidatorsComposer.validate(
                password,
                onSuccess = { isValid.set(true) },
                onError = { s: String -> _passwordError.value = s }
        )

        return isValid.get()
    }

    fun validateLogin(login: String): Boolean {
        val isValid = AtomicBoolean(false)
        val loginValidatorsComposer = Validator()
        loginValidatorsComposer.add(EmptyValidatorRule())
        loginValidatorsComposer.add(EmailValidateRule())

        loginValidatorsComposer.validate(
                login,
                onSuccess = { isValid.set(true) },
                onError = { s: String -> _loginError.value = s }
        )

        return isValid.get()
    }

    fun validateName(name: String): Boolean {
        val isValid = AtomicBoolean(false)
        val nameValidatorsComposer = Validator()
        nameValidatorsComposer.add(EmptyValidatorRule())

        nameValidatorsComposer.validate(
                name,
                onSuccess = { isValid.set(true) },
                onError = { s: String -> _nameError.value = s }
        )

        return isValid.get()
    }

    override fun onCleared() {
        super.onCleared()
        _signUpDisposable?.dispose()
    }
}