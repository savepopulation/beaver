package com.raqun.beaverlib.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MetaDataEntity::class], version = Db.Config.DB_VERSION, exportSchema = true)
abstract class BeaverDb : RoomDatabase() {
    abstract fun metaDataDao(): MetaDataDao
}