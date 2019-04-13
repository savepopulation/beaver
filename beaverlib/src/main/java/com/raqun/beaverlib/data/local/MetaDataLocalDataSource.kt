package com.raqun.beaverlib.data.local

import com.raqun.beaverlib.data.DataSource
import com.raqun.beaverlib.model.MetaData

class MetaDataLocalDataSource(private val db: BeaverDb) : DataSource.Local<String, MetaData> {

    private val entityMapper: DbEntityMapper<MetaDataEntity, MetaData> = MetaDataEntityMapper()

    override fun get(key: String): MetaData? {
        val metaDataEntities = db.metaDataDao().getMetaData(key)
        return if (metaDataEntities.isEmpty()) {
            null
        } else {
            entityMapper.map(metaDataEntities[0])
        }
    }

    override fun put(key: String, data: MetaData): Boolean {
        val metadataEntity = entityMapper.map(data)
        return db.metaDataDao().addMetaData(metadataEntity) > 0
    }

    override fun remove(key: String): Boolean {
        db.metaDataDao().deletMetaData(key)
        return true
    }

    override fun clear() {
        db.metaDataDao().nukeTable()
    }
}