package com.social.social.projection

interface PostProjection {
    fun getId() : Long
    fun getUserid() : Long
    fun getCaption() : String
    fun getDate() : String
    fun getTime() : String
    fun getLikecount() : Long
    fun getCommentcount() : Long
    fun getUsername() : String
    fun getProfileImage() : String
    fun getIsLike() : Boolean
    fun getIsSave() : Boolean


}