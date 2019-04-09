package com.raqun.beaverlib.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Db.TABLES.METADATA.NAME)
class MetaDataEntity constructor(
    @PrimaryKey @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.RAW_URL) val rawUrl: String,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.URL) val url: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.TITLE) val title: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.DESC) val desc: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.IMAGE_URL) val imageUrl: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.NAME) val name: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.MEDIA_TYPE) val mediaType: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.FAV_ICON) val favIcon: String?,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.CREATE_DATE) val createDate: Long,
    @ColumnInfo(name = Db.TABLES.METADATA.COLUMNS.UPDATE_DATE) val updateDate: Long
) : DbEntity