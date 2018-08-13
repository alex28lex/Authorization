package com.mgrsys.authorization.authorize.util

import android.os.Build

import com.magorasystems.protocolapi.request.auth.ClientAuthMeta
import com.magorasystems.protocolapi.request.auth.Platform

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
object MetaDataUtils {
    val deviceType: String
        get() = "android"

    val platform: String
        get() = deviceType + "_" + Build.VERSION.SDK_INT

    val deviceId: String
        get() = "35" + Build.BOARD.length % 10 + Build.BRAND.length % 10 +
                Build.DEVICE.length % 10 + Build.DISPLAY.length % 10 +
                Build.HOST.length % 10 + Build.ID.length % 10 +
                Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 +
                Build.PRODUCT.length % 10 + Build.TAGS.length % 10 +
                Build.TYPE.length % 10 + Build.USER.length % 10

    val meta: ClientAuthMeta
        get() = ClientAuthMeta(Platform.android(platform), deviceId, "1.0", deviceId)
}
