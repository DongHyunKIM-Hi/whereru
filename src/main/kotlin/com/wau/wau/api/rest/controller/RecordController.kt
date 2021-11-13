package com.wau.wau.api.rest.controller

import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.service.RecordService
import com.wau.wau.common.model.response.FileUploadResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

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

    @PostMapping("/post/images")
    fun uploadImages(
        @RequestParam("images", required = true) images: List<MultipartFile>,
        @RequestParam("userId", required = true) userId: String,
        @RequestParam("recordId", required = true) recordId: String
    ): ResponseEntity<List<FileUploadResponse>> {
        val response: List<FileUploadResponse> = recordService.uploadImages(userId,recordId.toInt(), images)
        return ResponseEntity.ok(response)
    }


}