package com.raqun.beaverlib.parser

import com.raqun.beaverlib.model.MetaData
import com.raqun.beaverlib.util.getHost
import com.raqun.beaverlib.util.resolve
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class DefaultMetaDataParser(private val timeout: Int = TIMEOUT_INMILIS) :
    MetaDataParser {

    override suspend fun parse(url: String): MetaData? {
        try {
            val metaData = MetaData(url)
            metaData.url = url

            val doc: Document = Jsoup.connect(url)
                .timeout(timeout)
                .get()

            val elements = doc.getElementsByTag(TAG_META)

            // Parse Title
            var title = doc.select(TAG_TITLE).attr(ATTR_CONTENT)
            if (title.isNullOrEmpty()) {
                title = doc.title()
            }
            metaData.title = title

            // Parse Description
            var desc = doc.select(TAG_DESC).attr(ATTR_CONTENT)
            if (desc.isNullOrEmpty()) {
                desc = doc.select(TAG_DESC_UPPER).attr(ATTR_CONTENT)
            }
            if (desc.isNullOrEmpty()) {
                desc = doc.select(TAG_DESC_PROP).attr(ATTR_CONTENT)
            }

            metaData.desc = desc

            // Parse images
            val imageElements = doc.select(TAG_IMAGES)
            if (imageElements.size > 0) {
                val image = imageElements.attr(ATTR_CONTENT)
                if (!image.isEmpty()) {
                    metaData.imageUrl = url.resolve(image)
                }
            }

            if (metaData.imageUrl.isNullOrEmpty()) {
                var src = doc.select(TAG_IMG_SRC).attr(ATTR_HREF)
                if (!src.isEmpty()) {
                    metaData.imageUrl = url.resolve(src)
                } else {
                    src = doc.select(TAG_APPLE_TOUCH_ICON).attr(
                        ATTR_HREF
                    )
                    if (!src.isEmpty()) {
                        metaData.imageUrl = url.resolve(src)
                        metaData.favIcon = url.resolve(src)
                    } else {
                        src = doc.select(TAG_ICON).attr(ATTR_HREF)
                        if (!src.isEmpty()) {
                            metaData.imageUrl = url.resolve(src)
                            metaData.favIcon = url.resolve(src)
                        }
                    }
                }
            }

            // Parse MediaType
            val mediaTypes = doc.select(TAG_MEDIA)
            val type = if (mediaTypes.size > 0) {
                val media = mediaTypes.attr(ATTR_CONTENT)
                if (media == "image") "photo" else media
            } else {
                doc.select(TAG_OGYPE).attr(ATTR_CONTENT)
            }
            metaData.mediaType = type

            // Favicon
            var src = doc.select(TAG_APPLE_TOUCH_ICON).attr(
                ATTR_HREF
            )
            if (!src.isEmpty()) {
                metaData.favIcon = url.resolve(src)
            } else {
                src = doc.select(TAG_ICON).attr(ATTR_HREF)
                if (!src.isEmpty()) {
                    metaData.favIcon = url.resolve(src)
                }
            }

            for (element in elements) {
                if (element.hasAttr(ATTR_PROPERTY)) {
                    val prop = element.attr(ATTR_PROPERTY).toString().trim()
                    if (prop == TAG_OGURL) {
                        metaData.url = element.attr(ATTR_CONTENT).toString()
                    }
                    if (prop == TAG_OGNAME) {
                        metaData.name = element.attr(ATTR_CONTENT).toString()
                    }
                }
            }

            if (metaData.url.isNullOrEmpty()) {
                metaData.url = url.getHost()
            }

            return metaData
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    companion object {

        // Config
        private const val TIMEOUT_INMILIS: Int = 15 * 1000

        // Tags
        private const val TAG_META = "meta"
        private const val TAG_TITLE = "$TAG_META[property=og:title]"
        private const val TAG_DESC = "$TAG_META[name=description]"
        private const val TAG_DESC_UPPER = "$TAG_META[name=Description]"
        private const val TAG_DESC_PROP = "$TAG_META[property=og:description]"
        private const val TAG_IMAGES = "$TAG_META[property=og:image]"
        private const val TAG_LINK = "link"
        private const val TAG_IMG_SRC = "$TAG_LINK[rel=image_src]"
        private const val TAG_APPLE_TOUCH_ICON = "$TAG_LINK[rel=apple-touch-icon]"
        private const val TAG_ICON = "$TAG_LINK[rel=icon]"
        private const val TAG_MEDIA = "$TAG_META[name=medium]"
        private const val TAG_OGYPE = "$TAG_META[property=og:type]"
        private const val TAG_OGURL = "og:url"
        private const val TAG_OGNAME = "og:site_name"

        // Attrs
        private const val ATTR_CONTENT = "content"
        private const val ATTR_HREF = "href"
        private const val ATTR_PROPERTY = "property"

    }
}