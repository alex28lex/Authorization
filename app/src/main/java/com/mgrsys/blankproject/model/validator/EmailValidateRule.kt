package com.mgrsys.blankproject.model.validator

import android.util.Patterns
import com.mgrsys.authorization.authorize.application.manager.ResExtractor
import com.mgrsys.blankproject.R
import ru.whalemare.rxvalidator.ValidateRule

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class EmailValidateRule : ValidateRule {
  override fun errorMessage(): String {

    return ResExtractor.instance.getString(R.string.error_validation_email)
  }

  override fun validate(data: String?): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(data).matches()
  }
}
