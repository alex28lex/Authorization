package com.mgrsys.authorization.authorize.screen.signup;

import com.mgrsys.authorization.authorize.model.view.MessageView;
import com.mgrsys.authorization.authorize.model.view.ProgressView;


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author mihaylov
 */
public interface SignUpContract {

    interface View extends ProgressView, MessageView {
        void setPasswordValidationError(String message);

        void setEmailValidationError(String message);

        void setNameValidationError(String message);
    }

    interface ViewModel {


    }

}
