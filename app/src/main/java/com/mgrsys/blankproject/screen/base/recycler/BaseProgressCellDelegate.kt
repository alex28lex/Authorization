package com.mgrsys.blankproject.screen.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgrsys.blankproject.R

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
abstract class BaseProgressCellDelegate<T> : BaseCellDelegate<T>() {

  override fun holder(parent: ViewGroup): BaseViewHolder<T> {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.item_progress, parent, false)
    return ProgressViewHolder(view)
  }

  inner class ProgressViewHolder(itemView: View) : BaseViewHolder<T>(itemView) {
    override fun bind(item: T) {
      // Do nothing.
    }
  }
}
