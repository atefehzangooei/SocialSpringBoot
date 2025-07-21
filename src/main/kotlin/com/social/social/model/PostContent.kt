package com.social.social.model

import jakarta.persistence.*

@Entity
@Table(name = "post_content")
data class PostContent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post : Post,

    @Column(name = "image", nullable = false)
    val image : String
)