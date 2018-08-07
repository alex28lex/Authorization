package com.mgrsys.blankproject.application.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
object ConnectionUtil {

  fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = connectivityManager.activeNetworkInfo
    return info != null && info.isConnected
  }

  fun registerConnectionChangedReceiver(context: Context, receiver: BroadcastReceiver) {
    context.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
  }

  fun unregisterConnectionChangedReceiver(context: Context, receiver: BroadcastReceiver) {
    context.unregisterReceiver(receiver)
  }
}
