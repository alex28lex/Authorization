package com.mgrsys.blankproject.application.util

import android.view.ViewGroup

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
object ViewUtils {

  fun toggleEnableState(rootViewGroup: ViewGroup, enabled: Boolean) {
    for (i in 0 until rootViewGroup.childCount) {
      val child = rootViewGroup.getChildAt(i)
      child.isEnabled = enabled
      if (child is ViewGroup) {
        toggleEnableState(child, enabled)
      }
    }
  }
}