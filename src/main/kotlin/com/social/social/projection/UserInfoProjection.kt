package com.social.social.projection

interface UserInfoProjection {
    fun getId() : Long
    fun getUsername() : String
    fun getPassword() : String
    fun getEmail() : String
    fun getPhone() : String
    fun getProfileImage() : String
    fun getBio() : String
    fun getLink() : String
    fun getFollower() : Int
    fun getFollowing() : Int
    fun getPostCount() : Int
}