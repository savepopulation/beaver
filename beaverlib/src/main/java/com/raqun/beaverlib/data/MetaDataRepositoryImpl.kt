package com.raqun.beaverlib.data

import com.raqun.beaverlib.model.MetaData
import com.raqun.beaverlib.util.AsyncManager
import kotlinx.coroutines.Deferred

class MetaDataRepositoryImpl(
    private val metaDataRemoteDataSource: DataSource.Remote<String, MetaData>,
    private val metaDataLocalDataSource: DataSource.Local<String, MetaData>?,
    private val metaDataCacheDataSource: DataSource.Cache<String, MetaData>?,
    asyncManager: AsyncManager
) : MetaDataRepository, AsyncManager by asyncManager {

    override suspend fun getMetaData(
        url: String,
        forceRefresh: Boolean
    ): Deferred<MetaData?> = handleAsync {

        if (forceRefresh) {
            return@handleAsync metaDataRemoteDataSource.getMetaData(url)
        }

        val cachedMetaData = metaDataCacheDataSource?.get(url)
        if (cachedMetaData != null) {
            return@handleAsync cachedMetaData
        }

        val localMetaData = metaDataLocalDataSource?.get(url)
        if (localMetaData != null) {
            metaDataCacheDataSource?.put(url, localMetaData)
            return@handleAsync localMetaData
        }

        val remoteMetaData = metaDataRemoteDataSource.getMetaData(url)
        if (remoteMetaData != null) {
            metaDataCacheDataSource?.put(url, remoteMetaData)
            metaDataLocalDataSource?.let {
                handleAsync { it.put(url, remoteMetaData) }
            }
        }

        remoteMetaData
    }

    @Synchronized
    override fun dropCache() {
        metaDataCacheDataSource?.drop()
    }

    @Synchronized
    override fun drop() {
        destroy()
        metaDataCacheDataSource?.drop()
    }
}