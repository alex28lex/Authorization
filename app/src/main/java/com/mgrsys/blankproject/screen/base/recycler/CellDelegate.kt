package com.mgrsys.blankproject.screen.base.recycler

import android.view.ViewGroup

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface CellDelegate<T> {

  fun `is`(item: T): Boolean

  fun type(): Int

  fun holder(parent: ViewGroup): BaseViewHolder<T>

  fun bind(holder: BaseViewHolder<T>, item: T)
}
