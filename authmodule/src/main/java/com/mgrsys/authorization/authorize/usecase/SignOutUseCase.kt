package com.mgrsys.authorization.authorize.usecase

import com.mgrsys.authorization.authorize.application.manager.SessionManager
import com.mgrsys.authorization.authorize.model.repository.AuthRepository
import io.paperdb.Paper
import io.reactivex.Flowable

/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
class SignOutUseCase(private val _authRepository: AuthRepository,
                     private val _sessionManager: SessionManager) {
    fun signOut(): Flowable<Boolean> {

        return _authRepository.signOut().map {
            true
        }.doOnNext {
            _sessionManager.logout()
            clearAllStorage()
        }

    }

    private fun clearAllStorage() {
        val keys = Paper.book().allKeys
        for (key in keys) {
            Paper.book().delete(key)
        }
    }
}