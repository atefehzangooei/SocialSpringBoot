package com.social.social.dto

import org.springframework.web.multipart.MultipartFile
import java.io.File

data class StoryRequest (
    val userId : Long,
    val date : String,
    val time : String
)