package com.mgrsys.authorization.authorize.model.repository

import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.ResponseCodes
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.magorasystems.protocolapi.response.auth.AuthResponseData
import com.magorasystems.protocolapi.response.auth.StringAuthInfo
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import com.mgrsys.authorization.authorize.model.dataobject.ClientRegistrationRequest
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import com.mgrsys.authorization.authorize.model.datasource.rest.AuthRestClient
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Developed by Magora Team (magora-systems.com). 2018 .
 *
 * @author mihaylov
 */
class MockRestAuthRepository @Inject constructor(
        private val usersRestClient: AuthRestClient
) : AuthRepository {



    override fun signIn(request: ClientAuthRequest): Flowable<AuthorizeResponse> {
        return Flowable.create({ emitter ->
            val authResponseData = AuthResponseData("awesome_token", 12345678893L,
                    "refresh_token", StringAuthInfo("display name", "ID"))
            val authorizeResponse = AuthorizeResponse(ResponseCodes.SUCCESS_CODE, authResponseData)
            emitter.onNext(authorizeResponse)
            emitter.onComplete()
        }, BackpressureStrategy.DROP)
    }

    override fun signUp(request: ClientRegistrationRequest<RegistrationData>): Flowable<AuthorizeResponse> {
        return Flowable.create({ emitter ->
            val authResponseData = AuthResponseData("awesome_token", 12345678893L,
                    "refresh_token", StringAuthInfo("display name", "ID"))
            val authorizeResponse = AuthorizeResponse(ResponseCodes.SUCCESS_CODE, authResponseData)
            emitter.onNext(authorizeResponse)
            emitter.onComplete()
        }, BackpressureStrategy.DROP)
    }

    override fun signOut(): Flowable<SuccessEmptyResponse> {
        return Flowable.create({ emitter ->
            val authorizeResponse = SuccessEmptyResponse(ResponseCodes.SUCCESS_CODE)
            emitter.onNext(authorizeResponse)
            emitter.onComplete()
        }, BackpressureStrategy.DROP)
    }
}
