package com.mgrsys.blankproject.application.di

import android.content.Context
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

}