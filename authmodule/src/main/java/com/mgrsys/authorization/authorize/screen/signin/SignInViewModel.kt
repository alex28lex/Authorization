package com.magorasystems.pmtoolpush.screen.authorize

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject



import javax.inject.Inject

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
class SignInViewModel : ViewModel() {
/*    @Inject
    lateinit var authUseCase: AuthorizeUseCase*/
    private val _navigateToMain = MutableLiveData<Unit>()
    val navigateToMain: LiveData<Unit>
        get() = _navigateToMain


    private val _authorizeSuccess = MutableLiveData<ViewObject<Unit>>()
    val authorizeSuccess: LiveData<ViewObject<Unit>>
        get() = _authorizeSuccess


 /*   private val _authorizePmToolServiceLiveData = MutableLiveData<ViewObject<PmToolService>>()
    val authorizePmToolServiceLiveData: LiveData<ViewObject<PmToolService>>
        get() = _authorizePmToolServiceLiveData

    private var _authorizeDisposable: Disposable? = null

    fun authorize(credentials: CredentialsVo) {
        _authorizeDisposable?.dispose()


        _authorizeDisposable =  authUseCase.authorize(credentials.login,credentials.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _authorizePmToolServiceLiveData.value = ViewObject.Success(it) },
                        { _authorizePmToolServiceLiveData.value = ViewObject.Error(it) },
                        {
                            val successCount =
                                    pmToolServiceManager.services.count { it.status == PmToolService.Status.AUTHORIZED }
                            if (successCount > 0) {
                                _authorizeSuccess.value = ViewObject.Success(Unit)
                                _navigateToMain.value = Unit
                            } else {
                                _authorizeSuccess.value = ViewObject.Error()
                            }
                        },
                        { _authorizeSuccess.value = ViewObject.Loading() }
                )
    }
*/
    override fun onCleared() {
        super.onCleared()
       // _authorizeDisposable?.dispose()
    }
}