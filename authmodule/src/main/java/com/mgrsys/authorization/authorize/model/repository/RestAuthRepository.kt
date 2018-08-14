package com.mgrsys.authorization.authorize.model.repository

import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import com.mgrsys.authorization.authorize.model.dataobject.ChangePassData
import com.mgrsys.authorization.authorize.model.dataobject.ClientRegistrationRequest
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import com.mgrsys.authorization.authorize.model.datasource.rest.AuthRestClient
import io.reactivex.Flowable
import javax.inject.Inject

class RestAuthRepository @Inject constructor(
        private val authRestClient: AuthRestClient
) : AuthRepository {
    override fun signIn(request: ClientAuthRequest): Flowable<AuthorizeResponse> {
        return authRestClient.signIn(request)
    }

    override fun signUp(request: ClientRegistrationRequest<RegistrationData>): Flowable<AuthorizeResponse> {
        return authRestClient.signUp(request)
    }

    override fun signOut(): Flowable<SuccessEmptyResponse> {
        return authRestClient.signOut()
    }

    override fun changePass(request: ChangePassData): Flowable<SuccessEmptyResponse> {
        return authRestClient.changePass(request)
    }
}