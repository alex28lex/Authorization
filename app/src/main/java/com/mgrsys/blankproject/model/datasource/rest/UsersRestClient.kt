package com.mgrsys.blankproject.model.datasource.rest

import com.mgrsys.blankproject.model.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface UsersRestClient {

  @GET("users/{login}")
  fun user(@Path("login") login: String): Single<User>

  @GET("users")
  fun users(): Single<List<User>>
}
