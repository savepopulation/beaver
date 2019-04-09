package com.raqun.beaverlib.data.local

import com.raqun.beaverlib.model.MetaData

class MetaDataEntityMapper : DbEntityMapper<MetaDataEntity, MetaData> {

    override fun map(entity: MetaDataEntity): MetaData {
        return MetaData(
            entity.rawUrl,
            entity.url,
            entity.title,
            entity.desc,
            entity.imageUrl,
            entity.name,
            entity.mediaType,
            entity.favIcon
        )
    }

    override fun map(domainObject: MetaData): MetaDataEntity {
        return MetaDataEntity(
            domainObject.rawUrl,
            domainObject.url,
            domainObject.title,
            domainObject.desc,
            domainObject.imageUrl,
            domainObject.name,
            domainObject.mediaType,
            domainObject.favIcon,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
    }
}

