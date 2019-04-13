package com.raqun.beaverlib.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MetaDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMetaData(metaData: MetaDataEntity): Long

    @Query("SELECT * FROM metadata WHERE raw_url = :rawUrl")
    fun getMetaData(rawUrl: String): List<MetaDataEntity>

    @Query("DELETE FROM metadata WHERE raw_url = :rawUrl")
    fun deletMetaData(rawUrl: String)

    @Query("DELETE FROM metadata")
    fun nukeTable()
}