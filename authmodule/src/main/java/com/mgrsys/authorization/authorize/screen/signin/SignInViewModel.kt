package com.magorasystems.pmtoolpush.screen.authorize


import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.screen.viewobject.auth.CredentialsVo
import com.magorasystems.pmtoolpush.util.livedata.SingleLiveEvent
import com.mgrsys.authorization.authorize.application.manager.ErrorHandler
import com.mgrsys.authorization.authorize.model.validator.PasswordValidatorRule
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.model.usecase.SignInUseCase
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
 * Developed 2018.
 *
 * @author mihaylov
 */
class SignInViewModel : ViewModel() {
    @Inject
    lateinit var authUseCase: SignInUseCase

    @Inject
    lateinit var router: Router

    private val _signInSuccess = SingleLiveEvent<ViewObject<Unit>>()
    val signInSuccess: LiveData<ViewObject<Unit>>
        get() = _signInSuccess

    private val _passwordError = SingleLiveEvent<String>()
    val passwordError: LiveData<String>
        get() = _passwordError

    private val _loginError = SingleLiveEvent<String>()
    val loginError: LiveData<String>
        get() = _loginError


    private var _signInDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    fun routeToSignUp() {
        router.navigateTo(Screens.SIGN_UP)
    }

    fun signIn(credentials: CredentialsVo) {
        if (validateLogin(credentials.login) and validatePassword(credentials.password)) {
            _signInDisposable?.dispose()

            _signInSuccess.value = ViewObject.Loading()
            _signInDisposable = authUseCase.signIn(credentials.login, credentials.password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { _signInSuccess.value = ViewObject.Success(Unit) },
                            { _signInSuccess.value = ViewObject.Error(ErrorHandler.parseError(it)) },
                            { router.navigateTo(Screens.MAIN) }
                    )
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

    override fun onCleared() {
        super.onCleared()
        _signInDisposable?.dispose()
    }
}