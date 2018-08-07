package com.mgrsys.blankproject.model.datasource.rest.constant

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
object RestOptions {
  const val URL_SEPARATOR = "/"
  const val HEADER_SEPARATOR = ": "

  const val HEADER_KEY_AUTH = "Authorization"
  const val HEADER_VALUE_BEARER = "Bearer "

  const val HEADER_KEY_CONTENT_TYPE = "Content-Type"
  const val HEADER_VALUE_CONTENT_TYPE_IMAGE = "image/png"

  const val HEADER_KEY_MARKER = "Marker"
  const val HEADER_VALUE_MARKER_NON_AUTH = "marker-non-auth"

  const val TIMEOUT_CONNECTION_SECONDS = 15L
  const val TIMEOUT_READ_SECONDS = 15L
  const val TIMEOUT_WRITE_SECONDS = 15L
}