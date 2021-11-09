package com.wau.wau.api.rest.service

import com.wau.wau.api.rest.model.entity.Record
import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.model.response.of
import com.wau.wau.api.rest.repository.RecordRepository
import org.springframework.stereotype.Service

@Service
class RecordServiceImpl (
    private val recordRepository: RecordRepository
):RecordService{
    override fun saveRecord(requestRecord: RequestRecord): ResponseRecord {
        val record:  Record = Record().of(requestRecord)
        recordRepository.save(record);
        return of(record)
    }
}