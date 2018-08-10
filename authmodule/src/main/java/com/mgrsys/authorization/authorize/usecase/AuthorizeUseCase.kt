package com.mgrsys.authorization.authorize.usecase


import com.magorasystems.protocolapi.request.auth.ClientAuthMeta
import com.magorasystems.protocolapi.request.auth.ClientAuthRequest
import com.magorasystems.protocolapi.response.auth.AuthResponseData
import com.magorasystems.protocolapi.response.auth.StringAuthInfo
import com.mgrsys.authorization.authorize.provider.SessionDataProvider
import io.reactivex.Single


/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
class AuthorizeUseCase(private val _dataProvider: SessionDataProvider) {
  fun authorize(_login: String, _password: String): Single<AuthResponseData<StringAuthInfo>> {
    val meta = ClientAuthMeta(
     "",
      "",
      "",
      null
    )
    val metaAuthRequest = ClientAuthRequest(_login, _password, meta)

    return _dataProvider.authorize(metaAuthRequest)
  }
}