package com.mgrsys.blankproject.model.validator

import com.mgrsys.authorization.authmodule.R
import com.mgrsys.authorization.authorize.application.manager.ResExtractor
import ru.whalemare.rxvalidator.ValidateRule

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class EmptyValidatorRule : ValidateRule {
    override fun errorMessage(): String {
        return ResExtractor.instance.getString(R.string.error_validation_empty)

    }

    override fun validate(data: String?): Boolean {
        return !data.isNullOrEmpty()
    }
}
