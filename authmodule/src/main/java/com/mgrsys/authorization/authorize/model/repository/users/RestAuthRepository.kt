package com.mgrsys.blankproject.model.repository.users

import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.SuccessEmptyResponse
import com.mgrsys.authorization.authorize.model.dataobject.AuthorizeResponse
import com.mgrsys.authorization.authorize.model.datasource.rest.AuthRestClient
import com.mgrsys.authorization.authorize.model.repository.users.AuthRepository
import io.reactivex.Flowable

import io.reactivex.Single
import javax.inject.Inject

class RestAuthRepository @Inject constructor(
        private val usersRestClient: AuthRestClient
) : AuthRepository {
    override fun signIn(request: ClientAuthRequest): Flowable<AuthorizeResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signUp(request: ClientAuthRequest): Flowable<AuthorizeResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut(): Flowable<SuccessEmptyResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}