package com.mgrsys.blankproject.application.di

import com.mgrsys.blankproject.screen.base.BaseActivity
import com.mgrsys.blankproject.screen.userlist.UserListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent {

  fun inject(injectable: BaseActivity)

  fun inject(injectable: UserListViewModel)
}