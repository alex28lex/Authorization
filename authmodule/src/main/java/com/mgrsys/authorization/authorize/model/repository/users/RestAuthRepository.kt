package com.mgrsys.blankproject.model.repository.users

import com.mgrsys.authorization.authorize.model.datasource.rest.AuthRestClient

import io.reactivex.Single
import javax.inject.Inject

class RestAuthRepository @Inject constructor(
    private val usersRestClient: AuthRestClient
) : AuthRepository{
  override fun login(): Single<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}