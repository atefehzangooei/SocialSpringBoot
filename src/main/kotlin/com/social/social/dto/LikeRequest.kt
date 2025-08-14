package com.social.social.dto

data class LikeRequest (
    val postId : Long,
    val userId : Long,
    val date : String,
    val time : String
)