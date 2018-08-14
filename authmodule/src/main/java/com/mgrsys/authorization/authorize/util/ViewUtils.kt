package com.mgrsys.authorization.authorize.util

import android.support.design.widget.TextInputLayout

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author mihaylov
 */
object ViewUtils {

    fun setInputLayoutError(textInputLayout: TextInputLayout?, message: String?) {
        if (textInputLayout != null) {
            textInputLayout.error = message
        }
    }
    //endregion
}
