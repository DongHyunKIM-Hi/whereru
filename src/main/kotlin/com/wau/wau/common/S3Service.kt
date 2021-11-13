package com.wau.wau.common


import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.wau.wau.common.model.response.FileUploadResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class S3Service {
    @Value("\${cloud.aws.credentials.accessKey}")
    private lateinit var accessKey: String
    @Value("\${cloud.aws.credentials.secretKey}")
    private lateinit var secretKey: String
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String
    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String

    private val s3Client: AmazonS3 by lazy {
        AmazonS3ClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey,secretKey)))
            .withRegion(region)
            .build()
    }


    fun upload(file: MultipartFile, recordId:Int, userId:String): FileUploadResponse{
        val originName = file.originalFilename ?: ""
        val filePath = "$userId/$recordId/$originName"
        s3Client.putObject(PutObjectRequest(bucket,filePath,file.inputStream,null)
            .withCannedAcl(CannedAccessControlList.PublicRead))
        return FileUploadResponse(
            originName,
            filePath,
            userId,
            recordId
        )
    }

}