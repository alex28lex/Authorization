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
import com.mgrsys.blankproject.model.validator.EmptyValidatorRule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.whalemare.rxvalidator.Validator
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

    private val _navigateToMain = MutableLiveData<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToMain

    private val _authorizeSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _authorizeSuccess


    private var _authorizeDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    fun routeToSignUp() {
        router.navigateTo(Screens.SIGN_UP)
    }

    fun authorize(credentials: CredentialsVo) {
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

/*    internal fun validatePassword(password: String): Boolean {
        val isValid: Boolean
        val passwordValidatorsComposer = Validator()
        passwordValidatorsComposer.add(EmptyValidatorRule())
        passwordValidatorsComposer.add(PasswordValidatorRule())

        passwordValidatorsComposer.validate(password)
        passwordValidationError.setValue(passwordValidatorsComposer.getDescription())
        return isValid
    }

    internal fun validateEmail(email: String): Boolean {
        val isValid: Boolean
        val emailValidatorsComposer = ValidatorsComposer(EmptyValidator(), EmailValidator())
        isValid = emailValidatorsComposer.isValid(email)
        emailValidationError.setValue(emailValidatorsComposer.getDescription())
        return isValid
    }*/

    override fun onCleared() {
        super.onCleared()
        _authorizeDisposable?.dispose()
    }
}