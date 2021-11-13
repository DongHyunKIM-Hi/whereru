package com.wau.wau.api.rest.service

import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.common.model.response.FileUploadResponse
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface RecordService{
    fun saveRecord(requestRecord: RequestRecord): ResponseRecord
    fun getRecordList(): List<ResponseRecord>
    fun getRecordByUserIdList(userId:String): List<ResponseRecord>
    fun deleteRecord(recordId: Int): Boolean
    fun uploadImages(userId: String,recordId: Int, images:List<MultipartFile>):List<FileUploadResponse>
}