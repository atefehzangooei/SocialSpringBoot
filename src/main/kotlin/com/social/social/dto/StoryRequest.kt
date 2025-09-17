package com.social.social.dto

data class StoryRequest (
    val userId : Long,
    val image : String,
    val date : String,
    val time : String
)