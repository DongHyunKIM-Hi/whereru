package com.wau.wau.api.rest.service

import com.wau.wau.api.rest.model.entity.Record
import com.wau.wau.api.rest.model.request.RequestRecord
import com.wau.wau.api.rest.model.response.ResponseRecord
import com.wau.wau.api.rest.model.response.of
import com.wau.wau.api.rest.repository.RecordRepository
import com.wau.wau.common.S3Service
import com.wau.wau.common.model.response.FileUploadResponse
import javassist.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.util.stream.Collectors

@Service
class RecordServiceImpl (
    private val recordRepository: RecordRepository,
    private val s3Service: S3Service
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
        val record:Record = recordRepository.findById(recordId).orElseThrow { throw NotFoundException("삭제하려는 아이디를 찾을 수 없습니다.") }
        recordRepository.deleteById(record.id)
        return true
    }

    override fun uploadImages(userId: String, recordId: Int, images: List<MultipartFile>): List<FileUploadResponse> {
        val record: Record = recordRepository.findByIdAndUserId(recordId,userId).orElseThrow { throw NotFoundException("조건에 맞는 아이디랑 기록을 찾을 수 없습니다.") }
        val imageList: List<FileUploadResponse> = images.stream().map { it -> s3Service.upload(it,recordId,userId) }.collect(Collectors.toList())
        var tmp : String = ""
        var totalUrl :String = imageList.stream().map { it-> tmp + it.imageUrl + ";;" }.collect(Collectors.joining())
        record.totalImageUrl = totalUrl;
        recordRepository.save(record)
        return imageList
    }
}