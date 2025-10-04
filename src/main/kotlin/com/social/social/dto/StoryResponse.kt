package com.social.social.dto

data class StoryResponse (
    val userId : Long,
    val profileImage : String,
    val username : String,
    val image : String,
    val date : String,
    val time : String
)