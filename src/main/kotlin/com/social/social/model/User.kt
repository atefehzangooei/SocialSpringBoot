package com.social.social.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @Column(name = "username", nullable = false, unique = true)
    val username : String,

    @Column(name = "phone", nullable = false)
    val phone : String,

    @Column(name = "password", nullable = false)
    val password : String,

    @Column(name = "link")
    val link : String,

    @Column(name = "date")
    val date : String,

    @Column(name = "time")
    val time : String,

    @Column(name = "profile_image")
    val profileImage : String,

    @Column(name = "email")
    val email : String,

    @Column(name = "bio")
    val bio : String


)