package com.wau.wau.api.rest.controller

import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.service.RecordService
import com.wau.wau.common.model.response.FileUploadResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/record")
class RecordController(
    private val  recordService: RecordService
) {
    @ApiOperation(value = "공유하고자하는 장소의 정보를 입력해주세요", notes = "공유하고자하는 장소의 정보를 입력해주세요")
    @PostMapping
    fun saveRecord(
        @RequestBody  requestRecord: RequestRecord
    ):ResponseEntity<ResponseRecord>{
        val response: ResponseRecord = recordService.saveRecord(requestRecord)
        return ResponseEntity.ok(response)
    }

    @ApiOperation(value = "기록된 모든 정보를 불러온다.", notes = "기록된 모든 정보를 불러온다.")
    @GetMapping("/get-all")
    fun  getRecords():ResponseEntity<List<ResponseRecord>>{
        val response : List<ResponseRecord> = recordService.getRecordList()
        return ResponseEntity.ok(response)
    }
    @ApiOperation(value = "특정 사용자의 기록을 불러온다.", notes = "특정 사용자의 기록을 불러온다.")
    @GetMapping("/get/{userId}")
    fun getRecordByUserId(
        @PathVariable userId:String
    ): ResponseEntity<List<ResponseRecord>>{
        val response: List<ResponseRecord> = recordService.getRecordByUserIdList(userId)
        return ResponseEntity.ok(response)
    }
    @ApiOperation(value = "특정 기록을 삭제한다.", notes = "특정 기록을 삭제한다.")
    @DeleteMapping("/delete/{recordId}")
    fun deleteRecord(
        @PathVariable recordId: Int
    ):ResponseEntity<Boolean>{
        val response: Boolean = recordService.deleteRecord(recordId)
        return ResponseEntity.ok(response)
    }
    @ApiOperation(value = "이미지를 업로드한다.", notes = "이미지를 업로드한다.")
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