package com.mgrsys.blankproject.model.datasource.rest

import com.mgrsys.blankproject.model.datasource.rest.constant.RestOptions
import com.mgrsys.blankproject.model.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface UsersRestClient {

    @GET("users/{login}")
    @Headers(RestOptions.HEADER_KEY_MARKER + RestOptions.HEADER_SEPARATOR + RestOptions.HEADER_VALUE_MARKER_NON_AUTH)
    fun user(@Path("login") login: String): Single<User>

    @GET("users")
    @Headers(RestOptions.HEADER_KEY_MARKER + RestOptions.HEADER_SEPARATOR + RestOptions.HEADER_VALUE_MARKER_NON_AUTH)
    fun users(): Single<List<User>>
}
