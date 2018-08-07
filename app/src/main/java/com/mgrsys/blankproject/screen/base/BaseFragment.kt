package com.mgrsys.blankproject.screen.base

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.layout_empty.*
import kotlinx.android.synthetic.main.layout_progress.*

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
abstract class BaseFragment : Fragment(), EmptyView, ProgressView, MessageView {

  override fun setEmptyViewEnabled(enabled: Boolean) {
    emptyView?.apply { visibility = if (enabled) View.VISIBLE else View.GONE }
  }

  override fun setProgressViewEnabled(enabled: Boolean) {
    progressView?.apply { visibility = if (enabled) View.VISIBLE else View.GONE }
  }

  override fun showMessage(message: String) {
    view?.let { v ->
      message.let { msg ->
        Snackbar
            .make(v, msg, Snackbar.LENGTH_INDEFINITE)
            .setAction(android.R.string.ok, { /*Do nothing. Dismiss by default*/ })
            .show()
      }
    }
  }
}
