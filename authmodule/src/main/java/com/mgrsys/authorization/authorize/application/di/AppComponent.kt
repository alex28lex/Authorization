package com.mgrsys.blankproject.application.di


import com.magorasystems.pmtoolpush.screen.authorize.ChangePassViewModel
import com.magorasystems.pmtoolpush.screen.authorize.SignInViewModel
import com.mgrsys.authorization.authorize.screen.signout.SignOutViewModel
import com.mgrsys.authorization.authorize.screen.signup.SignUpViewModel
import com.mgrsys.blankproject.screen.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Singleton
@Component(modules = [AuthAppModule::class, AuthModuleRetrofitModule::class])
interface AuthModuleComponent {
    fun inject(injectable: SignInViewModel)
    fun inject(injectable: SignUpViewModel)
    fun inject(injectable: SignOutViewModel)
    fun inject(injectable: ChangePassViewModel)
    fun inject(injectable: BaseActivity)
}