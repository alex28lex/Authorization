package com.mgrsys.authorization.authorize

import android.content.Context
import com.mgrsys.blankproject.application.di.AppComponentHolder
import com.mgrsys.blankproject.application.di.AuthAppModule
import com.mgrsys.blankproject.application.di.DaggerAuthModuleComponent

/**
 * Developed by Magora Team (magora-systems.com). 2018 .
 *
 * @author mihaylov
 */
object AuthModuleInjector {
    fun buildComponent(context: Context) {
        AppComponentHolder.bindComponent(DaggerAuthModuleComponent.builder().authAppModule(AuthAppModule(context)).build())
    }
}
