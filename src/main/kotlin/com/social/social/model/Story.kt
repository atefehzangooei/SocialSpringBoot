package com.social.social.model

import jakarta.persistence.*

const val STORY_DURATION : Int = 5

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

    //duration in seconds
    @Column(name = "duration")
    val duration : Int

)