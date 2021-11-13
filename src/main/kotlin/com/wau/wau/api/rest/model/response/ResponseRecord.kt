package com.wau.wau.api.rest.model.response

import com.wau.wau.api.rest.model.entity.Record

data class ResponseRecord(
    var id : Int,
    var userId : String? = null,
    var longitude : Double?  = null,
    var latitude : Double? = null,
    var memo : String?  =  null,
    var loadAddress : String? = null,
    var totalImageUrl: String? = null
)

fun of(record: Record): ResponseRecord{
    return ResponseRecord(
        record.id,
        record.userId,
        record.longitude,
        record.latitude,
        record.memo,
        record.loadAddress,
        record.totalImageUrl
    )
}


