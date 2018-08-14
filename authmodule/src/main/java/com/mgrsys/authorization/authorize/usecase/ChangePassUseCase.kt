package com.mgrsys.authorization.authorize.usecase


import com.mgrsys.authorization.authorize.application.manager.SessionManager
import com.mgrsys.authorization.authorize.model.dataobject.ChangePassData
import com.mgrsys.authorization.authorize.model.repository.AuthRepository
import io.reactivex.Flowable


/**
 * Developed 2018.
 *
 * @author mihaylov
 */
class ChangePassUseCase(private val _authRepository: AuthRepository) {
    fun changePass(oldPass: String, newPass: String): Flowable<Boolean> {

        val changePassData = ChangePassData(oldPass, newPass)
        return _authRepository.changePass(changePassData).map {
            true
        }

    }
}