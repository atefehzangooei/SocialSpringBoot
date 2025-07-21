package com.social.social.dto

data class CommentReplay(

    val postId : Long,
    val userId : Long,
    val comment : String,
    val parentId : Long,
    val date : String,
    val time : String
)