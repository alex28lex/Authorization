package com.mgrsys.blankproject.application

import android.util.Log
import timber.log.Timber

/**
 * @since 2018
 * @author Anton Vlasov - whalemare
 */
class TimberReleaseTree : Timber.Tree() {
  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    when (priority) {
      Log.VERBOSE, Log.DEBUG -> {

      }
      Log.INFO, Log.WARN, Log.ERROR -> {

      }
      else -> {
        // Ignoring
        // For example: Crashlytics.logException(t);
      }
    }
  }
}
