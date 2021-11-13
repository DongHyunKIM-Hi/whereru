package com.wau.wau.api.rest.service

import com.wau.wau.api.rest.model.entity.Record
import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.model.response.of
import com.wau.wau.api.rest.repository.RecordRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class RecordServiceImpl (
    private val recordRepository: RecordRepository
):RecordService{

    override fun saveRecord(requestRecord: RequestRecord): ResponseRecord {
        val record:  Record = Record().of(requestRecord)
        recordRepository.save(record);
        return of(record)
    }

    override fun getRecordList(): List<ResponseRecord> {
        var recordList: List<Record> = recordRepository.findAll();
        var responseList : List<ResponseRecord> = recordList.stream().map { of(it) }.collect(Collectors.toList())
        return responseList
    }

    override fun getRecordByUserIdList(userId: String): List<ResponseRecord> {
        var recordList: List<Record> = recordRepository.findAllByUserId(userId);
        var responseList : List<ResponseRecord> = recordList.stream().map { of(it) }.collect(Collectors.toList())
        return responseList
    }

    override fun deleteRecord(recordId:Int): Boolean {
        val record:Record = recordRepository.findById(recordId).orElseThrow { NotFoundException("삭제하려는 아이디를 찾을 수 없습니다.") }
        recordRepository.deleteById(record.id)
        return true
    }

}