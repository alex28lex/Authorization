package com.mgrsys.authorization.authorize.provider

import com.magorasystems.protocolapi.request.auth.AuthMeta
import com.magorasystems.protocolapi.request.auth.MetaAuthRequest
import com.magorasystems.protocolapi.response.auth.AuthResponseData
import com.magorasystems.protocolapi.response.auth.StringAuthInfo
import io.reactivex.Single

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
interface SessionDataProvider {
  fun <M : AuthMeta, T : MetaAuthRequest<M>> authorize(_request: T): Single<AuthResponseData<StringAuthInfo>>
}