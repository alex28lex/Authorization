package com.mgrsys.blankproject.screen.base.recycler

import android.view.View

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface OnCellDelegateClickListener<T> {
  fun onCellDelegateClick(itemView: View, position: Int, item: T?)
}
