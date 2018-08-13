package com.mgrsys.authorization.authorize.model.dataobject


import com.magorasystems.protocolapi.request.auth.ClientAuthMeta
import com.magorasystems.protocolapi.request.auth.ClientAuthRequest

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */

class ClientRegistrationRequest<T>(login: String, password: String, meta: ClientAuthMeta, internal var data: T)
    : ClientAuthRequest(login, password, meta) {

}
