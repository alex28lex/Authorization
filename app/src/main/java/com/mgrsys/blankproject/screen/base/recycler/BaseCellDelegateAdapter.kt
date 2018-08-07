package com.mgrsys.blankproject.screen.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
open class BaseCellDelegateAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

  private val cellDelegateManager: CellDelegateManager<T> = BaseCellDelegateManager()
  private val items = mutableListOf<T>()

  //region RecyclerView.Adapter section
  override fun getItemViewType(position: Int): Int {
    val item = items[position]
    return cellDelegateManager.getDelegate(item).type()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
    return cellDelegateManager.getDelegate(viewType).holder(parent)
  }

  override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
    val item = items[position]
    cellDelegateManager.getDelegate(item).bind(holder, item)
  }

  override fun getItemCount() = items.size
  //endregion

  //region CellDelegateManager section
  @SafeVarargs
  fun setCellDelegates(vararg cellDelegates: CellDelegate<T>) {
    cellDelegateManager.setDelegates(*cellDelegates)
  }
  //endregion

  //region Items section
  @Synchronized
  fun getItem(position: Int): T? {
    return try {
      items[position]
    } catch (e: IndexOutOfBoundsException) {
      null
    }
  }

  @Synchronized
  fun getItems(): List<T> {
    return items
  }

  @Synchronized
  fun setItem(position: Int, item: T?) {
    item?.let {
      if (items.size > position) {
        items[position] = item
        notifyItemChanged(position)
      } else {
        items.add(item)
        val positionInsert = items.size - 1
        notifyItemInserted(positionInsert)
      }
    }
  }

  @Synchronized
  fun setItems(items: List<T>?) {
    // fixme:
    // maybe if items == null need also clear items and not ignore that?
    // but i don`t change implementation, just refactor
    items?.let {
      this.items.clear()
      this.items.addAll(items)
      notifyDataSetChanged()
    }
  }

  @Synchronized
  fun addItems(items: List<T>?) {
    items?.let {
      val positionStart = this.items.size
      val itemCount = items.size
      this.items.addAll(items)
      notifyItemRangeInserted(positionStart, itemCount)
    }
  }

  @Synchronized
  fun removeItem(item: T?) {
    item?.let {
      val removeIndex = items.indexOf(item)
      if (removeIndex == INDEX_INVALID) {
        return
      }
      items.removeAt(removeIndex)
      notifyItemRemoved(removeIndex)
    }
  }

  companion object {
    private val INDEX_INVALID = -1
  }
  //endregion
}
