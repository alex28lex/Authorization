package com.mgrsys.authorization.authorize.application.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.magorasystems.protocolapi.response.ErrorResponse;
import com.magorasystems.protocolapi.response.ResponseCodes;
import com.magorasystems.protocolapi.response.ResponseErrorField;
import com.magorasystems.protocolapi.response.auth.AuthResponseCodes;
import com.mgrsys.authorization.authmodule.R;
import com.mgrsys.authorization.authorize.model.exception.RetrofitException;
import com.mgrsys.authorization.authorize.model.view.MessageView;


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author mihaylov
 */
public final class ErrorHandler {

    public static <VIEW extends MessageView> void handleError(@NonNull Throwable error,
                                                              @NonNull VIEW view) {
        error.printStackTrace();

        if (error instanceof RetrofitException) {
            final RetrofitException retrofitException = (RetrofitException) error;
            ErrorResponse responseDto = getRetrofitExceptionResponse(retrofitException);
            if (responseDto != null && !responseDto.getErrors().isEmpty()) {

                for (ResponseErrorField field : responseDto.getErrors()) {
                    switch (field.getCode()) {
                        case AuthResponseCodes.INVALID_AUTH_DATA:
                        case ResponseCodes.ERROR_COMMON_SECURITY_CODE:
                        case AuthResponseCodes.LOGIN_SHOULD_BE_CONFIRMED:
                        case AuthResponseCodes.REFRESH_TOKEN_INVALID_ERROR:
                        case AuthResponseCodes.ACCESS_TOKEN_INVALID_ERROR:
                        case AuthResponseCodes.ACCESS_TOKEN_EXPIRED_ERROR:
                        case AuthResponseCodes.REFRESH_TOKEN_EXPIRED_ERROR:
                        case AuthResponseCodes.PASS_CODE_NOT_VALID:
                        case AuthResponseCodes.INVALID_CHECK_CODE:
                        case AuthResponseCodes.USER_BLOCKED:
                        case AuthResponseCodes.COMMON_FORBIDDEN_ERROR:
                            view.showMessage(field.getMessage());
                            break;
                        default:
                            view.showMessage(ResExtractor.Companion.getInstance().getString(R.string.error_unknown));
                            break;
                    }
                }
                return;
            } else if (retrofitException.getKind().equals(RetrofitException.Kind.NETWORK)) {
                String message = !TextUtils.isEmpty(retrofitException.getMessage()) ?
                        retrofitException.getMessage() : ResExtractor.Companion.getInstance().getString(R.string.error_network);
                view.showMessage(message);
                return;
            } else {
                view.showMessage(error.getLocalizedMessage());
                return;
            }

        }
    }

    @Nullable
    protected static ErrorResponse getRetrofitExceptionResponse(Throwable error) {
        final RetrofitException retrofitException = (RetrofitException) error;
        try {
            final ErrorResponse errorResponse = retrofitException.getErrorBodyAs(ErrorResponse.class);
            if (errorResponse != null) {
                return errorResponse;
            }
            Log.v("ErrorResponse", "error " + errorResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

