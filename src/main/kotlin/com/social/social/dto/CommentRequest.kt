package com.social.social.dto

data class CommentRequest(
    val postId : Long,
    val userId : Long,
    val comment : String,
    val date : String,
    val time : String
)