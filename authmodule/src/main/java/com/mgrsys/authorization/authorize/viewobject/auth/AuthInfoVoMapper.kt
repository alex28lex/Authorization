package com.magorasystems.pmtoolpush.screen.viewobject.auth

import com.magorasystems.protocolapi.response.auth.AuthInfo
import java.io.Serializable

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
object AuthInfoVoMapper {

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable> fromDto(_authInfo: AuthInfo<T>): AuthInfoVo<T> {
        return AuthInfoVo(_authInfo.displayName, userId = _authInfo.userId)
    }
}