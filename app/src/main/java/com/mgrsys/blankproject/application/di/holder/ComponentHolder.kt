package com.mgrsys.blankproject.application.di.holder

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
interface ComponentHolder<T> {

  fun component(): T?

  fun bindComponent(component: T)

  fun unbindComponent()
}