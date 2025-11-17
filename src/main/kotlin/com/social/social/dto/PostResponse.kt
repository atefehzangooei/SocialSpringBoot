package com.social.social.dto

data class PostResponse(
    val id : Long,
    val caption : String,
    val date : String,
    val time : String,
    val userId : Long,
    val userProfile : String,
    val username : String,
    val likeCount : Long = 0,
    val commentCount : Long = 0,
    val image : String,
    val isLike : Boolean = false,
    val isSave : Boolean = false
)