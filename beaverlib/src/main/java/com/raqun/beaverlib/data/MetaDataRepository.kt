package com.raqun.beaverlib.data

import com.raqun.beaverlib.model.MetaData
import kotlinx.coroutines.Deferred

interface MetaDataRepository {
    suspend fun getMetaData(
        url: String,
        forceRefresh: Boolean = false,
        forceLocal: Boolean = false
    ): Deferred<MetaData?>

    fun dropCache()

    fun dropLocalCache(url: String? = null)

    fun drop()
}