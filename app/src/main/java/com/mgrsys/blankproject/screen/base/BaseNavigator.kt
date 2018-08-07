package com.mgrsys.blankproject.screen.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.mgrsys.blankproject.screen.Screens
import ru.terrakok.cicerone.android.SupportAppNavigator

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
abstract class BaseNavigator(
    activity: FragmentActivity,
    containerId: Int
) : SupportAppNavigator(activity, containerId) {

  override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
    return when (screenKey) {
      Screens.AUTH_REQUIRED -> null /*AuthActivity.newIntent(context) with ActivityFlags*/
      else -> createIntent(context, screenKey, data)
    }
  }

  abstract fun createIntent(context: Context?, screenKey: String?, data: Any?): Intent?
}