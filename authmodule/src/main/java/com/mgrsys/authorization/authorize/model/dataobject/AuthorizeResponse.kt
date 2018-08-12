package com.mgrsys.authorization.authorize.model.dataobject


import com.fasterxml.jackson.annotation.JsonProperty
import com.magorasystems.protocolapi.response.ResponseCodes
import com.magorasystems.protocolapi.response.auth.AuthResponseData
import com.magorasystems.protocolapi.response.auth.AuthSuccessResponse
import com.magorasystems.protocolapi.response.auth.StringAuthInfo

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
data class AuthorizeResponse(@JsonProperty("code") private val _code: String = ResponseCodes.SUCCESS_CODE,
                             @JsonProperty("data") private val _data: AuthResponseData<StringAuthInfo>? = null) :
        AuthSuccessResponse<AuthResponseData<StringAuthInfo>>(_code, _data)