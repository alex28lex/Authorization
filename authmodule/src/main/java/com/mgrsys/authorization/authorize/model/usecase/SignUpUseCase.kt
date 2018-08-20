package com.mgrsys.authorization.authorize.model.usecase

import com.magorasystems.pmtoolpush.application.rest.config.token.SimpleTokenConfig
import com.magorasystems.pmtoolpush.screen.viewobject.auth.StringAuthInfoVo
import com.magorasystems.protocolapi.response.auth.AuthResponseData
import com.magorasystems.protocolapi.response.auth.StringAuthInfo
import com.mgrsys.authorization.authorize.application.manager.SessionManager
import com.mgrsys.authorization.authorize.model.dataobject.ClientRegistrationRequest
import com.mgrsys.authorization.authorize.model.dataobject.RegistrationData
import com.mgrsys.authorization.authorize.model.repository.AuthRepository
import com.mgrsys.authorization.authorize.util.MetaDataUtils

import io.reactivex.Flowable

/**
 * Developed by Magora Team (magora-systems.com). 2018 .
 *
 * @author mihaylov
 */
class SignUpUseCase(private val authRepository: AuthRepository, private val sessionManager: SessionManager) {

    fun signUp(login: String, password: String, registrationData: RegistrationData): Flowable<AuthResponseData<StringAuthInfo>> {
        val clientRegistrationRequest = ClientRegistrationRequest(login, password,
                MetaDataUtils.meta, registrationData)
        return authRepository.signUp(clientRegistrationRequest).map {
            it.data
        }.doOnNext {
            sessionManager.setToken(
                    SimpleTokenConfig(
                            _accessToken = it.accessToken,
                            _refreshToken = it.refreshToken
                    )
            )
            it?.authInfo?.let {
                sessionManager.setUser(StringAuthInfoVo(it.displayName, it.userId))
            }
        }
    }
}
