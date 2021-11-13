package com.wau.wau.api.rest.model.entity

import com.wau.wau.api.rest.model.request.RequestRecord
import javax.persistence.*

@Entity
class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int = 0
    var userId : String? = null
    var longitude : Double?  = null
    var latitude : Double? = null
    var memo : String ? = null
    var loadAddress : String? = null
    @Column(length = 10000)
    var totalImageUrl : String? = null

    fun of(requestRecord: RequestRecord) : Record{
        return Record().apply {
            this.userId = requestRecord.userId
            this.longitude = requestRecord.longitude
            this.latitude = requestRecord.latitude
            this.memo = requestRecord.memo
            this.loadAddress = requestRecord.loadAddress
        }
    }
}
