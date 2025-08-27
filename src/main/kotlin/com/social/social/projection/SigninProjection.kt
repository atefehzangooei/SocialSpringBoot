package com.social.social.projection

interface SigninProjection {
    fun getId() : Long
    fun getUsername() : String
    fun getPhone() : String
    fun getPassword() : String
    fun getLink() : String
    fun getDate() : String
    fun getTime() : String
    fun getBio() : String
    fun getEmail() : String
    fun getProfileImage() : String
}