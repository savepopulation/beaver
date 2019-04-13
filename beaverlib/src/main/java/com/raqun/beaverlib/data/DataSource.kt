package com.raqun.beaverlib.data

interface DataSource {

    interface Remote<REQ, RES> : DataSource {
        suspend fun getMetaData(request: REQ): RES?
    }

    interface Local<KEY, VALUE> : DataSource {
        fun get(key: KEY): VALUE?

        fun put(key: KEY, data: VALUE): Boolean

        fun remove(key: KEY): Boolean

        fun clear()
    }

    interface Cache<KEY, VALUE> : DataSource {
        fun get(key: KEY): VALUE?

        fun put(key: KEY, value: VALUE): Boolean

        fun drop()
    }
}