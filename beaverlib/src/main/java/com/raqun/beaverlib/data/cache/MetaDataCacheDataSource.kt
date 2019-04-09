package com.raqun.beaverlib.data.cache

import com.raqun.beaverlib.data.DataSource
import com.raqun.beaverlib.model.MetaData

class MetaDataCacheDataSource : DataSource.Cache<String, MetaData> {

    private val cache = LinkedHashMap<String, MetaData>()

    @Synchronized
    override fun get(key: String): MetaData? = cache[key]

    @Synchronized
    override fun put(key: String, value: MetaData): Boolean {
        cache[key] = value
        return true
    }

    @Synchronized
    override fun drop() = cache.clear()
}