package com.mgrsys.blankproject.screen.base.recycler

import java.util.*

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
abstract class BaseCellDelegate<T> : CellDelegate<T> {
  private val TYPE = UUID.randomUUID().hashCode()

  override fun type(): Int {
    return TYPE
  }

  override fun bind(holder: BaseViewHolder<T>, item: T) {
    holder.bind(item)
  }
}
