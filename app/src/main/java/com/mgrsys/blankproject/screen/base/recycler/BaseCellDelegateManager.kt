package com.mgrsys.blankproject.screen.base.recycler

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
open class BaseCellDelegateManager<T> : CellDelegateManager<T> {
  private val delegates = mutableListOf<CellDelegate<T>>()

  @SafeVarargs
  override fun setDelegates(vararg delegates: CellDelegate<T>) {
    this.delegates.clear()
    this.delegates.addAll(delegates)
  }

  override fun getDelegate(item: T): CellDelegate<T> {
    return delegates.first { it.`is`(item) }
  }

  override fun getDelegate(viewType: Int): CellDelegate<T> {
    return delegates.first { it.type() == viewType }
  }
}
