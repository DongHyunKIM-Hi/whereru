package com.wau.wau.common.model.response

data class FileUploadResponse(
    var originName: String,
    var imagesPath: String,
    var userId : String,
    var recordId: Int,
    var imageUrl: String
)
