package com.social.social.model

import jakarta.persistence.*

const val LIFETIME : Int = 10

@Entity
@Table(name = "story")
data class Story (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "image", nullable = false)
    val image : String,

    @Column(name = "date")
    val date : String,

    @Column(name = "time")
    val time : String,

    //lifetime in seconds
    @Column(name = "lifetime")
    val lifetime : Int

)