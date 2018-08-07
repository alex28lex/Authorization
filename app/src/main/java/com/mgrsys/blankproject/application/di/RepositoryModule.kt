package com.mgrsys.blankproject.application.di

import android.content.Context
import com.mgrsys.blankproject.model.datasource.rest.UsersRestClient
import com.mgrsys.blankproject.model.repository.session.PrefsSessionRepository
import com.mgrsys.blankproject.model.repository.session.SessionRepository
import com.mgrsys.blankproject.model.repository.users.RestUsersRepository
import com.mgrsys.blankproject.model.repository.users.UsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Module
class RepositoryModule {

  @Singleton
  @Provides
  fun provideSessionRepository(context: Context): SessionRepository {
    return PrefsSessionRepository(context)
  }

  @Singleton
  @Provides
  fun provideUsersRepository(usersRestClient: UsersRestClient): UsersRepository {
    return RestUsersRepository(usersRestClient)
  }
}