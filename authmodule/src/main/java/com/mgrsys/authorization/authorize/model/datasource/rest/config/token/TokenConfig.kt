package com.magorasystems.pmtoolpush.application.rest.config.token

import java.io.Serializable

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
fun TokenConfig.bearerToken(): String {
    return "Bearer $accessToken"
}

interface TokenConfig : Serializable {

    val accessToken: String?

    val refreshToken: String?

    fun isRefreshTokenExist(): Boolean

}