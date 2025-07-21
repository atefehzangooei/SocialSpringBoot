package com.social.social.projection

interface CommentProjection {
    fun getId() : Long
    fun getUserId() : Long
    fun getUserProfile() : String
    fun getUsername() : String
    fun getComment() : String
    fun getDate() : String
    fun getTime() : String
    fun getPostId() : Long
}