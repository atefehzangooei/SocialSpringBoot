package com.social.social.entity

import jakarta.persistence.*

@Entity
@Table(name = "likes")

data class Like(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post : Post,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user : User,

    @Column(name = "date", nullable = false)
    val date : String,

    @Column(name = "time", nullable = false)
    val time : String

)