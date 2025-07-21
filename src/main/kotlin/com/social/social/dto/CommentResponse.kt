package com.social.social.dto

data class CommentResponse (
    val id : Long,
    val userId : Long,
    val userProfile : String,
    val username : String,
    val comment : String,
    val date : String,
    val time : String,
    val postId : Long
)