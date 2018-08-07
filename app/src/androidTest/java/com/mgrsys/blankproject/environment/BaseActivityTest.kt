package com.mgrsys.blankproject.environment

import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import junit.framework.Assert
import org.junit.Before
import java.util.concurrent.TimeUnit

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
open class BaseActivityTest<A : AppCompatActivity>(clazz: Class<A>) {

  protected val WAIT = TimeUnit.MINUTES.toMillis(1)
  protected val shortIdle: IdlingResource = ElapsedTimeIdlingResource(WAIT)
  protected val longIdle: IdlingResource = ElapsedTimeIdlingResource(WAIT * 30)

  val activityRule: ActivityTestRule<A> = ActivityTestRule(clazz)

  @Before
  open fun setup() {
    // override this method to setup your tests
  }

  protected fun handleIdle(@IdRes resId: Int) {
    Espresso.registerIdlingResources(shortIdle)
    Espresso.onView(ViewMatchers.withId(resId))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    Assert.assertEquals(4, 2 + 2)
    Espresso.unregisterIdlingResources(shortIdle)
  }

  protected fun getContext(): Context {
    return InstrumentationRegistry.getContext()
  }

  protected fun startActivity(intent: Intent? = null) {
    activityRule.launchActivity(intent)
  }

  protected fun getActivity(): A {
    return activityRule.activity
  }

  protected fun getFragment(): Fragment {
    val manager = activityRule.activity.supportFragmentManager
    return manager.fragments[manager.fragments.size - 1]
  }
}