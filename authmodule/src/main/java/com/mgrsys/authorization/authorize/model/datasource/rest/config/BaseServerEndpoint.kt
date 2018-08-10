package com.mgrsys.blankproject.model.datasource.rest.config

import com.mgrsys.blankproject.model.datasource.rest.constant.RestOptions
import java.lang.StringBuilder

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Valentin S.Bolkonsky
 * @author Viktor Zemtsov
 */
abstract class BaseServerEndpoint(
    private val host: String,
    private val path: String?,
    private val api: String?
) : ServerEndpoint {

  override fun url(): String {
    return with(StringBuilder()) {
      append(host)
      append(RestOptions.URL_SEPARATOR)

      if (!path.isNullOrBlank()) {
        append(path)
        append(RestOptions.URL_SEPARATOR)
      }

      if (!api.isNullOrBlank()) {
        append(api)
        append(RestOptions.URL_SEPARATOR)
      }

      toString()
    }
  }

  override fun toString(): String {
    return "BaseServerEndpoint(host = '$host', path = '$path', api = '$api')"
  }
}