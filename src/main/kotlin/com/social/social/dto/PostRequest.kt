package com.social.social.dto

data class PostRequest (

    val userId : Long,
    val caption : String,
    val date : String,
    val time : String
)