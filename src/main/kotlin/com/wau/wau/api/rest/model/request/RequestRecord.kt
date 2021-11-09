package com.wau.wau.api.rest.model.request

data class RequestRecord(
    val userId : String? = null,
    val longitude : Double?  = null,
    val latitude : Double? = null,
    val memo : String?  =  null,
    val loadAddress : String? = null,
)
