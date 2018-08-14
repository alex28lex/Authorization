package com.magorasystems.pmtoolpush.screen.authorize


import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.usecase.ChangePassUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
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

    private val _navigateToMain = MutableLiveData<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToMain

    private val _changePassSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _changePassSuccess


    private var _authorizeDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    fun routeToSignUp() {
        router.navigateTo(Screens.SIGN_UP)
    }

    fun changePass(oldPass: String, newPass: String, repeatNewPass: String) {
        _authorizeDisposable?.dispose()

        _changePassSuccess.value = ViewObject.Loading()
        _authorizeDisposable = changePassUseCase.changePass(newPass, repeatNewPass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _changePassSuccess.value = ViewObject.Success(Unit) },
                        { _changePassSuccess.value = ViewObject.Error(it) },
                        { router.navigateTo(Screens.MAIN) }
                )
    }

    override fun onCleared() {
        super.onCleared()
        _authorizeDisposable?.dispose()
    }
}