package com.mgrsys.blankproject.application

import android.app.Application
import com.mgrsys.authorization.authorize.AuthModuleInjector

import com.mgrsys.blankproject.BuildConfig
import com.mgrsys.blankproject.application.di.AppComponentHolder
import com.mgrsys.blankproject.application.di.AppModule
import com.mgrsys.blankproject.application.di.DaggerAppComponent
import timber.log.Timber

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 * @author Anton Vlasov - whalemare
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initDi()
    }

    private fun initTimber() {
        val tree = if (BuildConfig.DEBUG) Timber.DebugTree() else TimberReleaseTree()
        Timber.plant(tree)
    }

    private fun initDi() {
        // There is no need to unbind this component, because the system kills the process,
        // accordingly, all objects created by this process are destroyed.
      AuthModuleInjector.buildComponent(this)
        AppComponentHolder.bindComponent(DaggerAppComponent.builder().appModule(AppModule(this)).build())
    }
}
