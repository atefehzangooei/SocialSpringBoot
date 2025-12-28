package com.social.social.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post")
data class Post(
    @Id // is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //auto increment
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "image")
    val image : String,

    @Column(name = "caption", nullable = false)
    val caption : String,

    @Column(name = "date", nullable = false)
    val date : String,

    @Column(name = "time", nullable = false)
    val time : String,

)