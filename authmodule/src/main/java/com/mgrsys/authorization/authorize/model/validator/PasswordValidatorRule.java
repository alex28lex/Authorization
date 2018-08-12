package com.mgrsys.authorization.authorize.model.validator;


import com.mgrsys.authorization.authmodule.R;
import com.mgrsys.authorization.authorize.application.manager.ResExtractor;

import org.jetbrains.annotations.NotNull;

import ru.whalemare.rxvalidator.ValidateRule;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public class PasswordValidatorRule implements ValidateRule {

    @Override
    public boolean validate(String s) {

        if (s.length() < ResExtractor.Companion.getInstance().getInteger(R.integer.min_password_length)) {
            return false;
        }
        return true;
    }

    @NotNull
    @Override
    public String errorMessage() {
        return ResExtractor.Companion.getInstance().getString(R.string.change_pass_error_pass_length,
                ResExtractor.Companion.getInstance().getInteger(R.integer.min_password_length));
    }
}