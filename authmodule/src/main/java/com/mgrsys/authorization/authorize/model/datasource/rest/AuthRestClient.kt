package com.mgrsys.authorization.authorize.model.datasource.rest


import io.reactivex.Single
import retrofit2.http.GET


/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
interface AuthRestClient {
    @GET("users/{login}")
    fun login(): Single<Void>
}