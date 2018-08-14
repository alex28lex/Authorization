package com.mgrsys.authorization.authorize.model.validator


import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.application.manager.ResExtractor

import ru.whalemare.rxvalidator.ValidateRule

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
class PasswordValidatorRule : ValidateRule {
    override fun errorMessage(): String {
        return ResExtractor.instance.getString(R.string.change_pass_error_pass_length,
                ResExtractor.instance.getInteger(R.integer.min_password_length))
    }

    override fun validate(s: String?): Boolean {
        s?.let {
            return s.length >= ResExtractor.instance.getInteger(R.integer.min_password_length)
        }
        return false
    }
}