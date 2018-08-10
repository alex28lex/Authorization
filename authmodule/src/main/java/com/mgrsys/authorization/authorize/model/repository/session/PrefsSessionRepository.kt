package com.mgrsys.blankproject.model.repository.session

import android.content.Context
import android.content.SharedPreferences
import com.mgrsys.blankproject.model.entity.SessionInfo
import javax.inject.Inject

/**
 * Just for example.
 *
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
private const val TAG = "PREFS_SESSION_REPOSITORY_"
private const val KEY_ACCESS_TOKEN = TAG + "KEY_ACCESS_TOKEN"
private const val KEY_ACCESS_TOKEN_EXPIRE = TAG + "KEY_ACCESS_TOKEN_EXPIRE"
private const val KEY_REFRESH_TOKEN = TAG + "KEY_REFRESH_TOKEN"

class PrefsSessionRepository @Inject constructor(context: Context) : SessionRepository {
  private val prefs: SharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

  override fun sessionInfo(): SessionInfo {
    return SessionInfo(
        prefs.getString(KEY_ACCESS_TOKEN, null),
        prefs.getString(KEY_ACCESS_TOKEN_EXPIRE, null),
        prefs.getString(KEY_REFRESH_TOKEN, null)
    )
  }

  override fun setSessionInfo(sessionInfo: SessionInfo) {
    with(prefs.edit()) {
      putString(KEY_ACCESS_TOKEN, sessionInfo.accessToken)
      putString(KEY_ACCESS_TOKEN_EXPIRE, sessionInfo.accessTokenExpire)
      putString(KEY_REFRESH_TOKEN, sessionInfo.refreshToken)
      apply()
    }
  }

  override fun isAuthorized(): Boolean {
    return !sessionInfo().accessToken.isNullOrBlank()
  }
}