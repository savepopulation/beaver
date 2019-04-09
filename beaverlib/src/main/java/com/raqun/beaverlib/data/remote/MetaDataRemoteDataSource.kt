package com.raqun.beaverlib.data.remote

import com.raqun.beaverlib.data.DataSource
import com.raqun.beaverlib.model.MetaData
import com.raqun.beaverlib.parser.MetaDataParser

class MetaDataRemoteDataSource(private val metaDataParser: MetaDataParser) :
    DataSource.Remote<String, MetaData> {

    override suspend fun getMetaData(request: String): MetaData? {
        return metaDataParser.parse(request)
    }
}