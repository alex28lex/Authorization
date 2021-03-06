package com.mgrsys.blankproject.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.magorasystems.pmtoolpush.screen.authorize.ChangePassFragment
import com.magorasystems.pmtoolpush.screen.authorize.SignInFragment
import com.mgrsys.authorization.authorize.screen.Screens
import com.mgrsys.authorization.authorize.screen.signup.SignUpFragment
import com.mgrsys.blankproject.R
import com.mgrsys.blankproject.screen.base.BaseActivity
import com.mgrsys.blankproject.screen.userlist.UserListFragment
import ru.terrakok.cicerone.Navigator

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class AuthActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        router.replaceScreen(Screens.SIGN_IN)
    }

    override fun navigator(): Navigator {
        return object : com.mgrsys.blankproject.screen.base.BaseNavigator(this, R.id.containerViewGroup) {
            override fun createIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
                return null
            }

            override fun createFragment(screenKey: String?, data: Any?): Fragment? {
                return when (screenKey) {
                    Screens.SIGN_IN -> SignInFragment.newInstance()
                    Screens.SIGN_UP -> SignUpFragment.newInstance()
                    Screens.MAIN -> UserListFragment.newInstance()
                    Screens.CHANGE_PASS -> ChangePassFragment.newInstance()
                    else -> null
                }
            }
        }
    }
}