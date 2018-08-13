package com.mgrsys.authorization.authorize.screen.signout

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.mgrsys.authorization.authorize.usecase.SignOutUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignOutViewModel : ViewModel() {
    @Inject
    lateinit var signOutUseCase: SignOutUseCase

    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToLogin

    private val _authorizeSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _authorizeSuccess

    fun signOut() {
        signOutUseCase.signOut()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _authorizeSuccess.value = ViewObject.Success(Unit) },
                        { _authorizeSuccess.value = ViewObject.Error(it) },
                        { _navigateToLogin.value = Unit },
                        { _authorizeSuccess.value = ViewObject.Loading() }
                )
    }
}