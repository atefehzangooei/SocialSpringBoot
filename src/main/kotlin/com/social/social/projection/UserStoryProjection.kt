package com.social.social.projection

import com.social.social.dto.BASE_URL

interface UserStoryProjection {
    fun getProfileImage() : String
    fun getUsername() : String
    fun getImage(): String
    fun getDate() : String
    fun getTime() : String
    fun getDuration() : Int
    fun getImageUrl(imagePath : String): String = "$BASE_URL$imagePath"

}