package com.mgrsys.blankproject.model.datasource.rest.config

import com.mgrsys.blankproject.model.datasource.rest.constant.RestUrls

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
class SimpleServerEndpoint(
    host: String = RestUrls.HOST,
    path: String? = RestUrls.PATH,
    api: String? = RestUrls.API
) : BaseServerEndpoint(host, path, api)