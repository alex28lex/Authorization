package com.magorasystems.pmtoolpush.application.rest.config.token

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
data class SimpleTokenConfig(private val _accessToken: String? = null,
                             private val _refreshToken: String? = null) : TokenConfig {

    override val accessToken: String?
        get() = _accessToken
    override val refreshToken: String?
        get() = _refreshToken

    override fun isRefreshTokenExist(): Boolean {
        return _refreshToken != null
    }
}