package com.mgrsys.blankproject.application.di;

import com.mgrsys.blankproject.model.datasource.UsersMockDataSource;
import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com) 2017
 *
 * @author Viktor Zemtsov
 */
@Module
public class MockDataSourceModule {

  @Provides
  protected UsersDataSource provideUsersDataSource() {
    return new UsersMockDataSource();
  }
}
