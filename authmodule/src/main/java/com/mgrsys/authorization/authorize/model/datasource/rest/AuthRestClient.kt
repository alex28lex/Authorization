package com.mgrsys.authorization.authorize.model.datasource.rest


import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import com.mgrsys.authorization.authorize.model.dataobject.ChangePassData
import com.mgrsys.authorization.authorize.model.dataobject.ClientRegistrationRequest
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT


/**
Developed by Magora Team (magora-systems.com). 2018 .
 *
@author mihaylov
 */
interface AuthRestClient {
    @POST("auth/token")
    fun signIn(@Body request: ClientAuthRequest): Flowable<AuthorizeResponse>

    @POST("registration/users")
    fun signUp(@Body request: ClientRegistrationRequest<RegistrationData>): Flowable<AuthorizeResponse>

    @DELETE("auth/token")
    fun signOut(): Flowable<SuccessEmptyResponse>

    @PUT("auth/password")
    fun changePass(@Body request: ChangePassData): Flowable<SuccessEmptyResponse>

}