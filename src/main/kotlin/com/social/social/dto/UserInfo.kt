package com.social.social.dto

data class UserInfo(
    val id : Long,
    val username : String,
    val password : String,
    val email : String,
    val phone : String,
    val profileImage : String,
    val bio : String,
    val link : String,
    val follower : Int,
    val following : Int,
    val postCount : Int
)