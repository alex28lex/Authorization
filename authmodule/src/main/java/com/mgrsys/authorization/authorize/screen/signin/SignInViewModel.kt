package com.magorasystems.pmtoolpush.screen.authorize

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.screen.viewobject.auth.CredentialsVo
import com.mgrsys.authorization.authorize.usecase.SignInUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import javax.inject.Inject

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
class SignInViewModel : ViewModel() {
    @Inject
    lateinit var authUseCase: SignInUseCase

    private val _navigateToMain = MutableLiveData<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToMain

    private val _authorizeSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _authorizeSuccess


    private var _authorizeDisposable: Disposable? = null

    fun authorize(credentials: CredentialsVo) {
        _authorizeDisposable?.dispose()


        _authorizeDisposable = authUseCase.signIn(credentials.login, credentials.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            _authorizeSuccess.value = ViewObject.Success(Unit)
                            _navigateToMain.value = Unit
                        },
                        { _authorizeSuccess.value = ViewObject.Error(it) },
                        { },
                        { _authorizeSuccess.value = ViewObject.Loading() }
                )
    }

    override fun onCleared() {
        super.onCleared()
        _authorizeDisposable?.dispose()
    }
}