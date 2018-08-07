package com.mgrsys.blankproject.application.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
object KeyboardUtils {

  fun showKeyboard(editTextView: View) {
    editTextView.requestFocus()

    val context = editTextView.context
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager.showSoftInput(editTextView, InputMethodManager.SHOW_IMPLICIT)
  }

  fun hideKeyboard(anchorView: View) {
    val context = anchorView.context
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val windowToken = anchorView.windowToken

    inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
  }

  fun preventShowingKeyboard(activity: Activity) {
    activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
  }
}
