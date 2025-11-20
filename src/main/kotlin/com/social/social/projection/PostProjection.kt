package com.social.social.projection

import com.social.social.dto.BASE_URL

interface PostProjection {
    fun getId() : Long
    fun getUserid() : Long
    fun getCaption() : String
    fun getDate() : String
    fun getTime() : String
    fun getImage(): String
    fun getLikecount() : Long
    fun getCommentcount() : Long
    fun getUsername() : String
    fun getProfileImage() : String
    fun getIsLike() : Boolean
    fun getIsSave() : Boolean




}