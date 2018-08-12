package com.mgrsys.authorization.authorize.model.datasource.rest


import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.Response
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST


/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
interface AuthRestClient {
    @POST("auth/token")
    fun signIn(@Body request: ClientAuthRequest): Single<AuthorizeResponse>

    @POST("registration/users")
    fun signUp(@Body request: ClientAuthRequest): Single<AuthorizeResponse>

    @DELETE("auth/token")
    fun signOut(): Single<SuccessEmptyResponse>

}