package com.mgrsys.authorization.authorize

import android.app.Application
import com.mgrsys.authorization.authorize.application.manager.ResExtractor
import com.mgrsys.blankproject.application.di.AuthAppModule
import com.mgrsys.blankproject.application.di.DaggerAuthModuleComponent
import io.paperdb.Paper

/**
 * Developed by Magora Team (magora-systems.com). 2018 .
 *
 * @author mihaylov
 */
object AuthModuleInit {

    fun init(app: Application) {
        initAuthComponent(app)
        Paper.init(app)
        ResExtractor.instance.init(app)
    }

    private fun initAuthComponent(app: Application) {
        AppComponentHolder.bindComponent(DaggerAuthModuleComponent.builder().authAppModule(AuthAppModule(app.applicationContext)).build())
    }
}
