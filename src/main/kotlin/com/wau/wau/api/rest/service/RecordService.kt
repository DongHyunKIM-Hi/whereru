package com.wau.wau.api.rest.service

import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import org.springframework.stereotype.Service

@Service
interface RecordService{
    fun saveRecord(requestRecord: RequestRecord): ResponseRecord
    fun getRecordList(): List<ResponseRecord>
}