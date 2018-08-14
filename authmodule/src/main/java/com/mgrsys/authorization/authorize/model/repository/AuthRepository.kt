package com.mgrsys.authorization.authorize.model.repository

import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import com.mgrsys.authorization.authorize.model.dataobject.ChangePassData
import com.mgrsys.authorization.authorize.model.dataobject.ClientRegistrationRequest
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import io.reactivex.Flowable

/**
 * Developed by Magora Team (magora-systems.com). 2018.
 *
 * @author mihaylov
 */
interface AuthRepository {

    fun signIn(request: ClientAuthRequest): Flowable<AuthorizeResponse>

    fun signUp(request: ClientRegistrationRequest<RegistrationData>): Flowable<AuthorizeResponse>

    fun signOut(): Flowable<SuccessEmptyResponse>

    fun changePass(request: ChangePassData): Flowable<SuccessEmptyResponse>
}