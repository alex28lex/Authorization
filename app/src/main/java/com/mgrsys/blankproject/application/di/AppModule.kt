package com.mgrsys.blankproject.application.di

import android.content.Context
import com.mgrsys.blankproject.application.App
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
class AppModule(private val app: App) {
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
}