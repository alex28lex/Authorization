package com.mgrsys.blankproject.model.dataobject;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Developed by Magora Team (magora-systems.com) 2017
 *
 * @author Viktor Zemtsov
 */
public final class UserDtoMocker {

  public static UserDto getUserDto(@NonNull String login) {
    return UserDto.builder()
        .login(login)
        .build();
  }

  public static List<UserDto> getUserDtoList(int count) {
    final List<UserDto> dtoList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      dtoList.add(getUserDto("Login " + i));
    }
    return dtoList;
  }
}
