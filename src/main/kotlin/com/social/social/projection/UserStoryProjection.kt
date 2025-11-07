package com.social.social.projection

interface UserStoryProjection {
    fun getProfileImage() : String
    fun getUsername() : String
    fun getImage() : String
    fun getDate() : String
    fun getTime() : String
    fun getDuration() : Int
}