package com.social.social.model

import jakarta.persistence.*

@Entity
@Table(name = "followers")
data class Follower(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    val follower : User,

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    val following : User,

    @Column(name = "date")
    val date : String,

    @Column(name = "time")
    val time : String

)
