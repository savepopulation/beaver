package com.raqun.beaverlib.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raqun.beaverlib.model.MetaData

@Dao
interface MetaDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMetaData(metaData: MetaDataEntity): Long

    @Query("SELECT * FROM metadata WHERE url = :key")
    fun getMetaData(key: String): List<MetaDataEntity>
}