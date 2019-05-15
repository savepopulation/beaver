package com.raqun.beaverlib

import android.content.Context
import androidx.room.Room
import com.raqun.beaverlib.data.DataSource
import com.raqun.beaverlib.data.MetaDataRepository
import com.raqun.beaverlib.data.MetaDataRepositoryImpl
import com.raqun.beaverlib.data.cache.MetaDataCacheDataSource
import com.raqun.beaverlib.data.local.BeaverDb
import com.raqun.beaverlib.data.local.Db
import com.raqun.beaverlib.data.local.MetaDataLocalDataSource
import com.raqun.beaverlib.data.remote.MetaDataRemoteDataSource
import com.raqun.beaverlib.model.MetaData
import com.raqun.beaverlib.parser.DefaultMetaDataParser
import com.raqun.beaverlib.parser.MetaDataParser
import com.raqun.beaverlib.util.AsyncManager
import com.raqun.beaverlib.util.DefaultAsyncManager
import com.raqun.beaverlib.util.assertIsInitialized
import com.raqun.beaverlib.util.assertIsNotInitialized
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers

object Beaver {

    private var metaDataRepository: MetaDataRepository? = null

    fun build(
        context: Context,
        asyncManager: AsyncManager = DefaultAsyncManager(Dispatchers.IO),
        metaDataParser: MetaDataParser = DefaultMetaDataParser(),
        remoteDataSource: DataSource.Remote<String, MetaData> = MetaDataRemoteDataSource(
            metaDataParser
        ),
        localDataSource: DataSource.Local<String, MetaData>? = MetaDataLocalDataSource(
            Room.databaseBuilder(context.applicationContext, BeaverDb::class.java, Db.Config.DB_NAME).build()
        ),
        cacheDataSource: DataSource.Cache<String, MetaData>? = MetaDataCacheDataSource()
    ) {
        assertIsNotInitialized()
        metaDataRepository = MetaDataRepositoryImpl(
            remoteDataSource,
            localDataSource,
            cacheDataSource,
            asyncManager
        )
    }

    suspend fun load(
        url: String,
        forceRefresh: Boolean = false,
        forceLocal: Boolean = false
    ): Deferred<MetaData?> {
        assertIsInitialized()
        return metaDataRepository!!.getMetaData(url, forceRefresh, forceLocal)
    }

    fun dropCache() {
        assertIsInitialized()
        metaDataRepository!!.dropCache()
    }

    fun destroy() {
        assertIsInitialized()
        metaDataRepository!!.drop()
        metaDataRepository = null
    }

    fun dropLocalCache(url: String? = null) {
        assertIsInitialized()
        metaDataRepository!!.dropLocalCache(url)
    }

    fun isInitialized() = metaDataRepository != null
}