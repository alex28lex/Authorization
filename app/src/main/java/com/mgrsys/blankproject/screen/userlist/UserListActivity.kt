package com.mgrsys.blankproject.screen.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.magorasystems.pmtoolpush.screen.authorize.SignInFragment
import com.mgrsys.blankproject.R
import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.screen.Screens
import com.mgrsys.blankproject.screen.base.BaseActivity
import com.mgrsys.blankproject.screen.base.BaseNavigator
import com.mgrsys.blankproject.screen.userdetails.UserDetailsActivity
import ru.terrakok.cicerone.Navigator

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class UserListActivity : BaseActivity() {

  companion object {
    fun newIntent(context: Context?): Intent {
      return Intent(context, UserListActivity::class.java)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_list)

    router.replaceScreen(Screens.USER_LIST)
  }

  override fun navigator(): Navigator {
    return object : BaseNavigator(this, R.id.containerViewGroup) {
      override fun createIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        return when (screenKey) {
          Screens.USER_DETAILS -> UserDetailsActivity.newIntent(context, data as User)
          else -> null
        }
      }

      override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when (screenKey) {
          Screens.USER_LIST -> SignInFragment.newInstance()
          else -> null
        }
      }
    }
  }
}
