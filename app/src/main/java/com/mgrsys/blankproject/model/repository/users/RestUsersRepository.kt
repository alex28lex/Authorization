package com.mgrsys.blankproject.model.repository.users

import com.mgrsys.blankproject.model.datasource.rest.UsersRestClient
import com.mgrsys.blankproject.model.entity.User
import io.reactivex.Single
import javax.inject.Inject

class RestUsersRepository @Inject constructor(
    private val usersRestClient: UsersRestClient
) : UsersRepository {

  override fun user(login: String): Single<User> {
    // TODO: Handle cache
    return usersRestClient.user(login)
  }

  override fun users(): Single<List<User>> {
    // TODO: Handle cache
    return usersRestClient.users()
  }
}