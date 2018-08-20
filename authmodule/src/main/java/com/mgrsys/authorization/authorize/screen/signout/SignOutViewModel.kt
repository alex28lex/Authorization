package com.mgrsys.authorization.authorize.screen.signout

import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.util.livedata.SingleLiveEvent
import com.mgrsys.authorization.authorize.application.manager.ErrorHandler
import com.mgrsys.authorization.authorize.model.usecase.SignOutUseCase
import com.mgrsys.authorization.authorize.screen.Screens
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignOutViewModel : ViewModel() {
    @Inject
    lateinit var signOutUseCase: SignOutUseCase

    @Inject
    lateinit var router: Router

    private var _signOutDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    private val _navigateToLogin = SingleLiveEvent<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToLogin

    private val _authorizeSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _authorizeSuccess

    fun signOut() {
        _authorizeSuccess.value = ViewObject.Loading()
        signOutUseCase.signOut()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _authorizeSuccess.value = ViewObject.Success(Unit) },
                        { _authorizeSuccess.value = ViewObject.Error(ErrorHandler.parseError(it)) },
                        { router.navigateTo(Screens.SIGN_IN) }
                )
    }

    override fun onCleared() {
        super.onCleared()
        _signOutDisposable?.dispose()
    }
}