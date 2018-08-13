package com.mgrsys.blankproject.application.di

import android.content.Context
import com.mgrsys.authorization.authorize.application.manager.SessionManager
import com.mgrsys.authorization.authorize.model.datasource.rest.AuthRestClient
import com.mgrsys.authorization.authorize.model.repository.AuthRepository
import com.mgrsys.authorization.authorize.model.repository.MockRestAuthRepository
import com.mgrsys.authorization.authorize.usecase.SignInUseCase
import com.mgrsys.authorization.authorize.usecase.SignOutUseCase
import com.mgrsys.authorization.authorize.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Module
class AuthAppModule(private val app: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return app
    }

    @Provides
    fun provideSignInUseCase(_authRepository: AuthRepository,
                             _sessionManager: SessionManager): SignInUseCase {
        return SignInUseCase(_authRepository, _sessionManager)
    }

    @Provides
    fun provideSignUpUseCase(_authRepository: AuthRepository,
                             _sessionManager: SessionManager): SignUpUseCase {
        return SignUpUseCase(_authRepository, _sessionManager)
    }

    @Provides
    fun provideSignOutCase(_authRepository: AuthRepository,
                           _sessionManager: SessionManager): SignOutUseCase {
        return SignOutUseCase(_authRepository, _sessionManager)
    }

    @Singleton
    @Provides
    fun provideSessionManager(): SessionManager {
        return SessionManager(SessionManager::class.java.simpleName)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(_authRestClient: AuthRestClient): AuthRepository {
        return MockRestAuthRepository(_authRestClient)
    }

}