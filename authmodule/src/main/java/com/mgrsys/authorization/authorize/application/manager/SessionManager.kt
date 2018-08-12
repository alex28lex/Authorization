package com.mgrsys.authorization.authorize.application.manager


import com.magorasystems.pmtoolpush.application.rest.config.token.SimpleTokenConfig
import com.magorasystems.pmtoolpush.application.rest.config.token.TokenConfig
import com.magorasystems.pmtoolpush.application.rest.config.token.bearerToken
import com.magorasystems.pmtoolpush.screen.viewobject.auth.AuthInfoVo
import io.paperdb.Paper


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */

class SessionManager(
        tag: String
) {
    private val config = Config(tag)
    @field:Volatile
    private var token: TokenConfig
    @field:Volatile
    private var pushToken: String? = null
    @field:Volatile
    private var _user: AuthInfoVo<String>? = null

    init {

        val accessToken = Paper.book().read<String?>(config.keyAccessToken)
        val refreshToken = Paper.book().read<String?>(config.keyRefreshToken)
        token = SimpleTokenConfig(_accessToken = accessToken, _refreshToken = refreshToken)
        pushToken = Paper.book().read(config.keyPushToken)
        _user = Paper.book().read(config.keyUser)
    }

    fun getUser(): AuthInfoVo<String>? {
        return _user
    }

    fun setUser(user: AuthInfoVo<String>) {
        _user = user
        Paper.book().write(config.keyUser, user)
    }

    fun getToken(): TokenConfig {
        if (!token.isRefreshTokenExist()) {
            throw RuntimeException("token is not set")
        }
        return token
    }

    fun setToken(token: TokenConfig) {
        this.token = token
        Paper.book().write(config.keyAccessToken, token.accessToken)
        Paper.book().write(config.keyRefreshToken, token.refreshToken)
    }

    fun getPushToken(): String? {
        if (pushToken.isNullOrBlank()) {
            pushToken = Paper.book().read(config.keyPushToken)
        }
        return pushToken
    }

    fun setPushToken(pushToken: String) {
        this.pushToken = pushToken
        Paper.book().write(config.keyPushToken, pushToken)
    }

    fun isAuthorize(): Boolean {
        if (token.isRefreshTokenExist()) {
            val accessToken = Paper.book().read<String?>(config.keyAccessToken)
            val refreshToken = Paper.book().read<String?>(config.keyRefreshToken)
            token = SimpleTokenConfig(_accessToken = accessToken, _refreshToken = refreshToken)
        }
        return token.isRefreshTokenExist()
    }

    fun logout() {
        token = SimpleTokenConfig()
        Paper.book().delete(config.keyAccessToken)
        Paper.book().delete(config.keyRefreshToken)
        Paper.book().delete(config.keyUser)
    }

    private class Config(
            val tag: String
    ) {
        val keyAccessToken = tag + "_KEY_ACCESS_TOKEN"
        val keyRefreshToken = tag + "_KEY_REFRESH_TOKEN"
        val keyPushToken = tag + "_KEY_PUSH_TOKEN"
        val keyUser = tag + "_KEY_USER"
    }
}
