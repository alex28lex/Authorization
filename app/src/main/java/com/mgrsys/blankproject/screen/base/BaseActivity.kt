package com.mgrsys.blankproject.screen.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mgrsys.blankproject.application.di.AppComponentHolder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity : AppCompatActivity() {
  @Inject
  protected lateinit var router: Router
  @Inject
  protected lateinit var navigatorHolder: NavigatorHolder

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AppComponentHolder.component()?.inject(this)
  }

  override fun onResumeFragments() {
    super.onResumeFragments()
    navigatorHolder.setNavigator(navigator())
  }

  protected abstract fun navigator(): Navigator

  override fun onPause() {
    super.onPause()
    navigatorHolder.removeNavigator()
  }

  override fun onSupportNavigateUp(): Boolean {
    return onNavigateUp()
  }

  override fun onNavigateUp(): Boolean {
    onBackPressed()
    return true
  }
}
