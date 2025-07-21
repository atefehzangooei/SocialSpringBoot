package com.social.social.dto

data class SavePostRequest (
    val userId : Long,
    val postId : Long,
    val date : String,
    val time : String
)