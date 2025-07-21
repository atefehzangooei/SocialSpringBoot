package com.social.social.dto

data class FollowRequest(
    val followerId : Long,
    val followingId : Long,
    val date : String,
    val time : String
)