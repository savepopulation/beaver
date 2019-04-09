package com.raqun.beaverlib.data.local

import android.util.Log
import com.raqun.beaverlib.data.DataSource
import com.raqun.beaverlib.model.MetaData

class MetaDataLocalDataSource(private val db: BeaverDb) : DataSource.Local<String, MetaData> {

    private val entityMapper: DbEntityMapper<MetaDataEntity, MetaData> = MetaDataEntityMapper()

    override fun get(key: String): MetaData? {
        Log.e("gettin with", key)
        val metaDataEntities = db.metaDataDao().getMetaData(key)
        return if (metaDataEntities.isEmpty()) {
            null
        } else {
            entityMapper.map(metaDataEntities[0])
        }
    }

    override fun put(key: String, data: MetaData): Boolean {
        Log.e("putting with", key)
        val metadataEntity = entityMapper.map(data)
        val result = db.metaDataDao().addMetaData(metadataEntity) > 0
        Log.e("save result", "" + result)
        return result
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}