package com.mgrsys.blankproject.screen.base.recycler

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface CellDelegateManager<T> {

  fun setDelegates(vararg delegates: CellDelegate<T>)

  fun getDelegate(item: T): CellDelegate<T>

  fun getDelegate(viewType: Int): CellDelegate<T>
}
