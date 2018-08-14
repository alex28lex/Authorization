package com.mgrsys.authorization.authorize.screen.changepass;

import com.mgrsys.authorization.authorize.model.view.MessageView;
import com.mgrsys.authorization.authorize.model.view.ProgressView;


/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author mihaylov
 */
public interface ChangePassContract {

    interface View extends ProgressView, MessageView {
        void setOldPasswordValidationError(String message);

        void setNewPasswordValidationError(String message);

        void setRepeatNewPasswordValidationError(String message);
    }

    interface ViewModel {


    }

}
