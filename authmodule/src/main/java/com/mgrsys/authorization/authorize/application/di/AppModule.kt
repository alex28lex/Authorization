package com.mgrsys.blankproject.application.di

import android.content.Context
import com.mgrsys.authorization.authorize.application.manager.SessionManager
import com.mgrsys.authorization.authorize.model.datasource.rest.AuthRestClient
import com.mgrsys.authorization.authorize.model.repository.AuthRepository
import com.mgrsys.authorization.authorize.model.repository.MockRestAuthRepository
import com.mgrsys.authorization.authorize.usecase.ChangePassUseCase
import com.mgrsys.authorization.authorize.usecase.SignInUseCase
import com.mgrsys.authorization.authorize.usecase.SignOutUseCase
import com.mgrsys.authorization.authorize.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Module
class AuthAppModule(private val app: Context) {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    @Provides
    fun provideSignInUseCase(_authRepository: AuthRepository,
                             _sessionManager: SessionManager): SignInUseCase {
        return SignInUseCase(_authRepository, _sessionManager)
    }

    @Provides
    fun provideChangePassUseCase(_authRepository: AuthRepository): ChangePassUseCase {
        return ChangePassUseCase(_authRepository)
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