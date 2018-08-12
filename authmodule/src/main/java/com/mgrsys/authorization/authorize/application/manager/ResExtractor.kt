package com.mgrsys.authorization.authorize.application.manager

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.support.v4.content.ContextCompat


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class ResExtractor private constructor() {

    private var app: Application? = null

    fun init(app: Application) {
        this.app = app
    }

    var context: Context? = null
        private set
        get() {
            app?.let {
                return it.applicationContext
            }
            return null
        }

    fun getStringArray(@ArrayRes arrayRes: Int): Array<String> {
        checkInitialization()
        return app!!.resources.getStringArray(arrayRes)
    }

    fun getQuantityString(@PluralsRes pluralsRes: Int, quantity: Int, vararg formatArgs: Any): String {
        checkInitialization()
        return app!!.resources.getQuantityString(pluralsRes, quantity, *formatArgs)
    }

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        checkInitialization()
        return app!!.resources.getString(stringRes, *formatArgs)
    }

    fun getInteger(@IntegerRes integerRes: Int): Int {
        checkInitialization()
        return app!!.resources.getInteger(integerRes)
    }

    fun getColor(@ColorRes colorRes: Int): Int {
        checkInitialization()
        return ContextCompat.getColor(app!!.applicationContext, colorRes)
    }

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable {
        checkInitialization()
        return ContextCompat.getDrawable(app!!.applicationContext, drawableRes)!!
    }

    fun getDimensionPixel(@DimenRes dimenRes: Int): Int {
        checkInitialization()
        return app!!.resources.getDimensionPixelSize(dimenRes)
    }

    private fun checkInitialization() {
        if (app == null) {
            throw IllegalStateException("ResExtractor is not initialized. " + "You must call the init(App app) method before using this class.")
        }
    }

    companion object {
         val instance = ResExtractor()
    }
}
