package com.social.social.dto

data class LikeRequest (
    val postId : Long,
    val user_id : Long,
    val date : String,
    val time : String
)