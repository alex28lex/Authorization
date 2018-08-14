package com.mgrsys.authorization.authorize.screen.signin;

import com.mgrsys.authorization.authorize.model.view.MessageView;
import com.mgrsys.authorization.authorize.model.view.ProgressView;


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author mihaylov
 */
public interface SignInContract {

    interface View extends ProgressView, MessageView {
        void setPasswordValidationError(String message);

        void setEmailValidationError(String message);
    }

    interface ViewModel {


    }

}
