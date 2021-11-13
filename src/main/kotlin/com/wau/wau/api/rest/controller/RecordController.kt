package com.wau.wau.api.rest.controller

import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.service.RecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/record")
class RecordController(
    private val  recordService: RecordService
) {
    @PostMapping
    fun saveRecord(
        @RequestBody  requestRecord: RequestRecord
    ):ResponseEntity<ResponseRecord>{
        val response: ResponseRecord = recordService.saveRecord(requestRecord)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-all")
    fun  getRecords():ResponseEntity<List<ResponseRecord>>{
        val response : List<ResponseRecord> = recordService.getRecordList()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get/{userId}")
    fun getRecordByUserId(
        @PathVariable userId:String
    ): ResponseEntity<List<ResponseRecord>>{
        val response: List<ResponseRecord> = recordService.getRecordByUserIdList(userId)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete/{recordId}")
    fun deleteRecord(
        @PathVariable recordId: Int
    ):ResponseEntity<Boolean>{
        val response: Boolean = recordService.deleteRecord(recordId)
        return ResponseEntity.ok(response)
    }
}