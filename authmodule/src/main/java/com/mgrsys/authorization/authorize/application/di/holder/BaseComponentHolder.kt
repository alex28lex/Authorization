package com.mgrsys.blankproject.application.di.holder

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
abstract class BaseComponentHolder<T> : ComponentHolder<T> {
  private var component: T? = null

  override fun component(): T? {
    return component
  }

  override fun bindComponent(component: T) {
    this.component = component
  }

  override fun unbindComponent() {
    this.component = null
  }
}