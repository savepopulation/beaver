package com.raqun.beaverlib.parser

import com.raqun.beaverlib.model.MetaData

interface MetaDataParser {
    suspend fun parse(url: String): MetaData?
}