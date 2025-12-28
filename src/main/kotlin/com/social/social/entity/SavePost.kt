package com.social.social.entity

import jakarta.persistence.*

@Entity
@Table(name = "save_post")
data class SavePost (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user : User,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post : Post,

    @Column(name = "date")
    val date : String,

    @Column(name = "time")
    val time : String
)
