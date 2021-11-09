package com.wau.wau.api.rest.controller

import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.service.RecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}