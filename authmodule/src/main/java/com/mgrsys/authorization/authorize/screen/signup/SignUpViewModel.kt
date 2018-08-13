package com.mgrsys.authorization.authorize.screen.signup

import AppComponentHolder
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import com.mgrsys.authorization.authorize.usecase.SignUpUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignUpViewModel : ViewModel() {
    @Inject
    lateinit var signUpUseCase: SignUpUseCase

    private val _navigateToMain = MutableLiveData<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToMain

    private val _signUpSuccess = MutableLiveData<ViewObject<Unit>>()
    val signUpSuccess: LiveData<ViewObject<Unit>>
        get() = _signUpSuccess


    private var _signUpDisposable: Disposable? = null

    init {
        AppComponentHolder.component()?.inject(this)
    }

    fun signUp(login: String, password: String, userName: String) {
        _signUpDisposable?.dispose()


        _signUpDisposable = signUpUseCase.signUp(login, password, RegistrationData(userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _signUpSuccess.value = ViewObject.Success(Unit) },
                        { _signUpSuccess.value = ViewObject.Error(it) },
                        { _navigateToMain.value = Unit },
                        { _signUpSuccess.value = ViewObject.Loading() }
                )
    }

    override fun onCleared() {
        super.onCleared()
        //_authorizeDisposable?.dispose()
    }
}