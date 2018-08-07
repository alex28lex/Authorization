package com.mgrsys.blankproject.environment

import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.IdlingResource
import java.util.concurrent.TimeUnit

class ElapsedTimeIdlingResource(private val waitingTime: Long) : IdlingResource {

  init {
    IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.MILLISECONDS)
    IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.MILLISECONDS)
  }

  private val startTime: Long = System.currentTimeMillis()
  private var resourceCallback: IdlingResource.ResourceCallback? = null

  override fun getName(): String {
    return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
  }

  override fun isIdleNow(): Boolean {
    val elapsed = System.currentTimeMillis() - startTime
    val idle = elapsed >= waitingTime
    if (idle) {
      resourceCallback!!.onTransitionToIdle()
    }
    return idle
  }

  override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
    this.resourceCallback = resourceCallback
  }
}