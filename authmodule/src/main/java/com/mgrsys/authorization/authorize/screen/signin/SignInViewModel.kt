package com.magorasystems.pmtoolpush.screen.authorize


import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.screen.viewobject.auth.CredentialsVo
import com.mgrsys.authorization.authorize.model.validator.PasswordValidatorRule
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.usecase.SignInUseCase
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

    private val _authorizeSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _authorizeSuccess

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String>
        get() = _passwordError

    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String>
        get() = _loginError


    private var _authorizeDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    fun routeToSignUp() {
        router.navigateTo(Screens.SIGN_UP)
    }

    fun authorize(credentials: CredentialsVo) {
        if (validateLogin(credentials.login) and validatePassword(credentials.password)) {
            _authorizeDisposable?.dispose()

            _authorizeSuccess.value = ViewObject.Loading()
            _authorizeDisposable = authUseCase.signIn(credentials.login, credentials.password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { _authorizeSuccess.value = ViewObject.Success(Unit) },
                            { _authorizeSuccess.value = ViewObject.Error(it) },
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
        _authorizeDisposable?.dispose()
    }
}