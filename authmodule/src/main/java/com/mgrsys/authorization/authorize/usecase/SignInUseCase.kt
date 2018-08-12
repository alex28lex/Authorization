package com.mgrsys.authorization.authorize.usecase


import com.magorasystems.pmtoolpush.application.rest.config.token.SimpleTokenConfig
import com.magorasystems.pmtoolpush.screen.viewobject.auth.StringAuthInfoVo
import com.magorasystems.protocolapi.request.auth.ClientAuthMeta
import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.auth.AuthResponseData
import com.magorasystems.protocolapi.response.auth.StringAuthInfo
import com.mgrsys.authorization.authorize.application.manager.SessionManager
import com.mgrsys.authorization.authorize.model.repository.users.AuthRepository
import com.mgrsys.authorization.authorize.provider.SessionDataProvider
import com.mgrsys.blankproject.model.repository.users.RestAuthRepository
import io.reactivex.Flowable
import io.reactivex.Single


/**
 * Developed 2018.
 *
 * @author mihaylov
 */
class SignInUseCase(private val _authRepository: AuthRepository,
                    private val _sessionManager: SessionManager) {
    fun signIn(_login: String, _password: String): Flowable<AuthResponseData<StringAuthInfo>> {
        val meta = ClientAuthMeta(
                "",
                "",
                "",
                null
        )
        val metaAuthRequest = ClientAuthRequest(_login, _password, meta)
        return _authRepository.signIn(metaAuthRequest).map {
            it.data
        }.doOnNext {
            _sessionManager.setToken(
                    SimpleTokenConfig(
                            _accessToken = it.accessToken,
                            _refreshToken = it.refreshToken
                    )
            )
            it?.authInfo?.let {
                _sessionManager.setUser(StringAuthInfoVo(it.displayName, it.userId))
            }
        }

    }
}