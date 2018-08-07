package com.mgrsys.blankproject.model.usecase

import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.model.repository.users.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
class UsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

  fun execute(): Single<List<User>> {
    return usersRepository.users()
  }
}