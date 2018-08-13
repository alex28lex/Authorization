package com.mgrsys.authorization.authorize.model.repository

import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import com.mgrsys.authorization.authorize.model.dataobject.ClientRegistrationRequest
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.Body

/**
 * Developed by Magora Team (magora-systems.com). 2018.
 *
 * @author mihaylov
 */
interface AuthRepository {

    fun signIn(@Body request: ClientAuthRequest): Flowable<AuthorizeResponse>

    fun signUp(@Body request: ClientRegistrationRequest<RegistrationData>): Flowable<AuthorizeResponse>

    fun signOut(): Flowable<SuccessEmptyResponse>
}