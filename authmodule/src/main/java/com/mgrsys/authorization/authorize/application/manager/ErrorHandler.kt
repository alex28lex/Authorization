package com.mgrsys.authorization.authorize.application.manager

import android.text.TextUtils
import android.util.Log

import com.magorasystems.protocolapi.response.ErrorResponse
import com.magorasystems.protocolapi.response.ResponseCodes
import com.magorasystems.protocolapi.response.ResponseErrorField
import com.magorasystems.protocolapi.response.auth.AuthResponseCodes
import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.model.exception.RetrofitException


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author mihaylov
 */
object ErrorHandler {

    fun parseError(error: Throwable): String {
        error.printStackTrace()

        if (error is RetrofitException) {
            val responseDto = getRetrofitExceptionResponse(error)
            if (responseDto != null && !responseDto.errors.isEmpty()) {

                for (field in responseDto.errors) {
                    when (field.code) {
                        AuthResponseCodes.INVALID_AUTH_DATA, ResponseCodes.ERROR_COMMON_SECURITY_CODE, AuthResponseCodes.LOGIN_SHOULD_BE_CONFIRMED, AuthResponseCodes.REFRESH_TOKEN_INVALID_ERROR, AuthResponseCodes.ACCESS_TOKEN_INVALID_ERROR, AuthResponseCodes.ACCESS_TOKEN_EXPIRED_ERROR, AuthResponseCodes.REFRESH_TOKEN_EXPIRED_ERROR, AuthResponseCodes.PASS_CODE_NOT_VALID, AuthResponseCodes.INVALID_CHECK_CODE, AuthResponseCodes.USER_BLOCKED, AuthResponseCodes.COMMON_FORBIDDEN_ERROR -> return field.message
                        else -> return ResExtractor.instance.getString(R.string.error_unknown)
                    }
                }
            } else return if (error.kind == RetrofitException.Kind.NETWORK) {
                if (!TextUtils.isEmpty(error.message))
                    error.message!!
                else
                    ResExtractor.instance.getString(R.string.error_network)
            } else {
                error.getLocalizedMessage()
            }

        }
        return error.localizedMessage
    }

     fun getRetrofitExceptionResponse(error: Throwable): ErrorResponse? {
        val retrofitException = error as RetrofitException
        try {
            val errorResponse = retrofitException.getErrorBodyAs(ErrorResponse::class.java)
            if (errorResponse != null) {
                return errorResponse
            }
            Log.v("ErrorResponse", "error " + errorResponse!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


}

