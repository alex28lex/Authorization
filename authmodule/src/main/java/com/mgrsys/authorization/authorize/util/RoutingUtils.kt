package com.mgrsys.authorization.authorize.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
object RoutingUtils {
    private val TAG = RoutingUtils::class.java.simpleName
    private val BUNDLE = TAG + "_BUNDLE"

    fun startActivity(fromActivity: Activity,
                      toActivityClass: Class<out Activity>,
                      bundle: Bundle?) {
        startActivity(fromActivity, toActivityClass, null, bundle, false)
    }

    fun startActivity(fromActivity: Activity,
                      toActivityClass: Class<out Activity>,
                      finishFromActivity: Boolean) {
        startActivity(fromActivity, toActivityClass, null, null, finishFromActivity)
    }

    @JvmOverloads
    fun startActivity(fromActivity: Activity,
                      toActivityClass: Class<out Activity>,
                      activityFlags: Int? = null,
                      bundle: Bundle? = null,
                      finishFromActivity: Boolean = false) {
        val intent = Intent(fromActivity, toActivityClass)

        if (activityFlags != null) {
            intent.flags = activityFlags
        }

        if (bundle != null) {
            intent.putExtra(BUNDLE, bundle)
        }

        fromActivity.startActivity(intent)

        if (finishFromActivity) {
            fromActivity.finish()
        }
    }

    fun startAuthorizeActivity(fromActivity: Activity) {
/*        startActivity(fromActivity, AuthorizeActivity::class.java,
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)*/
    }
    //endregion

    //region ActivityForResult
    fun startForResult(fromActivity: Activity,
                       toActivityClass: Class<out Activity>,
                       requestCode: Int) {
        startForResult(fromActivity, toActivityClass, null, null, requestCode)
    }

    fun startForResult(fromActivity: Activity,
                       toActivityClass: Class<out Activity>,
                       activityFlags: Int?,
                       requestCode: Int) {
        startForResult(fromActivity, toActivityClass, activityFlags, null, requestCode)
    }

    fun startForResult(fromActivity: Activity,
                       toActivityClass: Class<out Activity>,
                       bundle: Bundle?,
                       requestCode: Int) {
        startForResult(fromActivity, toActivityClass, null, bundle, requestCode)
    }

    fun startForResult(fromActivity: Activity,
                       toActivityClass: Class<out Activity>,
                       activityFlags: Int?,
                       bundle: Bundle?,
                       requestCode: Int) {
        val intent = Intent(fromActivity, toActivityClass)

        if (activityFlags != null) {
            intent.flags = activityFlags
        }

        if (bundle != null) {
            intent.putExtra(BUNDLE, bundle)
        }

        fromActivity.startActivityForResult(intent, requestCode)
    }
    //endregion

    //region Fragment
    fun showFragment(activity: AppCompatActivity,
                     @IdRes containerViewId: Int,
                     fragment: Fragment) {
        showFragment(activity, null, containerViewId, fragment, false)
    }

    @JvmOverloads
    fun showFragment(activity: AppCompatActivity,
                     savedInstanceState: Bundle?,
                     @IdRes containerViewId: Int,
                     fragment: Fragment,
                     addToBackStack: Boolean = false) {
        if (savedInstanceState != null) {
            // After recreation. No action is required.
            return
        }

        val ft = activity.supportFragmentManager.beginTransaction()
        val fragmentTag = fragment.javaClass.name

        ft.replace(containerViewId, fragment, fragmentTag)

        if (addToBackStack) {
            ft.addToBackStack(fragmentTag)
        }

        ft.commit()
    }
    //endregion
}// Empty
//region Activity
