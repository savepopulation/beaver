package com.raqun.beaverlib.data.local

class Db private constructor() {

    object Config {
        const val DB_NAME = "beaver"
        const val DB_VERSION = 1
        const val CREATE_DATE = "create_date"
        const val UPDATE_DATE = "update_date"
    }

    object TABLES {

        object METADATA {
            const val NAME = "metadata"

            object COLUMNS {
                const val RAW_URL = "raw_url"
                const val URL = "url"
                const val TITLE = "title"
                const val DESC = "desc"
                const val IMAGE_URL = "image_url"
                const val NAME = "name"
                const val MEDIA_TYPE = "media_type"
                const val FAV_ICON = "fav_icon"
                const val CREATE_DATE = Config.CREATE_DATE
                const val UPDATE_DATE = Config.UPDATE_DATE
            }
        }
    }
}


