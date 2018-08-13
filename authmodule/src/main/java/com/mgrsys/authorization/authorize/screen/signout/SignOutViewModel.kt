package com.mgrsys.authorization.authorize.screen.signout

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.usecase.SignOutUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
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

    private val _navigateToLogin = MutableLiveData<Unit>()
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
                        { _authorizeSuccess.value = ViewObject.Error(it) },
                        { router.navigateTo(Screens.SIGN_IN)}
                )
    }
}