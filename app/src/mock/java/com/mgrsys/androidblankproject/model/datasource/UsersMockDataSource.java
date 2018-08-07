package com.mgrsys.blankproject.model.datasource;

import com.mgrsys.blankproject.model.dataobject.UserDtoMocker;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Developed by Magora Team (magora-systems.com) 2017
 *
 * @author Viktor Zemtsov
 */
public final class UsersMockDataSource implements UsersDataSource {

  @Override
  public Flowable<List<UserDto>> getUsers() {
    return Flowable.fromCallable(() -> UserDtoMocker.getUserDtoList(30));
  }

  @Override
  public Flowable<UserDto> getUser(String login) {
    return Flowable.fromCallable(() -> UserDtoMocker.getUserDto(login));
  }
}
