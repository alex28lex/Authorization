package com.magorasystems.pmtoolpush.screen.viewobject.auth

import java.io.Serializable

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
open class AuthInfoVo<out T: Serializable>(val displayName: String = "",
                                                val userId: T? = null,
                                                val permission: String? = null,
                                                val groups: String? = null)