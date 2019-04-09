package com.raqun.beaverlib.data.local

interface DbEntityMapper<R : DbEntity, T> {
    fun map(entity: R): T

    fun map(domainObject: T): R
}