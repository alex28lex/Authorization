package com.mgrsys.blankproject.screen.userdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.mgrsys.blankproject.R
import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.screen.Screens
import com.mgrsys.blankproject.screen.base.BaseActivity
import com.mgrsys.blankproject.screen.base.BaseNavigator
import ru.terrakok.cicerone.Navigator

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class UserDetailsActivity : BaseActivity() {

  companion object {
    fun newIntent(context: Context?, user: User): Intent {
      return Intent(context, UserDetailsActivity::class.java).apply {
        putExtra(UserDetailsConsts.KEY_USER, user)
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_details)

    // Just for example. With this approach, User goes through two stages of serialization / deserialization.
    router.replaceScreen(Screens.USER_DETAILS, intent.getParcelableExtra(UserDetailsConsts.KEY_USER))
  }

  override fun navigator(): Navigator {
    return object : BaseNavigator(this, R.id.containerViewGroup) {
      override fun createIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        return null
      }

      override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when (screenKey) {
          Screens.USER_DETAILS -> UserDetailsFragment.newInstance(data as User)
          else -> null
        }
      }
    }
  }
}
