package com.raqun.beaverlib.model

data class MetaData(
    var rawUrl: String,
    var url: String? = null,
    var title: String? = null,
    var desc: String? = null,
    var imageUrl: String? = null,
    var name: String? = null,
    var mediaType: String? = null,
    var favIcon: String? = null
)