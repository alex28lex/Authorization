package com.mgrsys.blankproject.model.validator

import ru.whalemare.rxvalidator.ValidateRule

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class EmptyValidatorRule : ValidateRule {
    override fun errorMessage(): String {
        // TODO: Inject Context
//    return ResExtractor.instance.getString(R.string.error_validation_empty)
        return ""
    }

    override fun validate(data: String?): Boolean {
        return !data.isNullOrEmpty()
    }
}
