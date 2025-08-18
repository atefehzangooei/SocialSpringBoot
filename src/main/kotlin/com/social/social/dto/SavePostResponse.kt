package com.social.social.dto

data class SavePostResponse (
    val userId : Long,
    val postId : Long,
    val date : String,
    val time : String
)