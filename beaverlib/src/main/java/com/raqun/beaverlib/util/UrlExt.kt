package com.raqun.beaverlib.util

import android.webkit.URLUtil
import java.net.URI
import java.net.URISyntaxException

/*
 * Resolves given url with part
 */
fun String.resolve(part: String): String? {
    if (URLUtil.isValidUrl(part)) {
        return part
    } else {
        var uri: URI? = null
        try {
            uri = URI(this)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            return null
        }
        uri = uri.resolve(part)
        return uri.toString()
    }
}

/*
 * Gets host of given url
 */
fun String.getHost(): String? {
    var uri: URI? = null
    return try {
        uri = URI(this)
        uri.host
    } catch (e: URISyntaxException) {
        null
    }
}