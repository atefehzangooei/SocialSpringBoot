package com.social.social.model

import jakarta.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post : Post,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user : User,

    @Column(name = "comment", nullable = false)
    val comment : String,

    @ManyToOne
    @JoinColumn(name = "comment_id")
    val parent : Comment? = null,

    @Column(name = "date")
    val date : String,

    @Column(name = "time")
    val time : String

)