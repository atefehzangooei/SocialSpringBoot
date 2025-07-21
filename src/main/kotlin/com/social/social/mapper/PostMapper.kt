package com.social.social.mapper

import com.social.social.dto.PostResponse
import com.social.social.model.Post


fun Post.toResponse() : PostResponse{
    return PostResponse(
        id = this.id,
        caption = this.caption,
        date = this.date,
        time = this.time,
        userId = this.user.id,
        userProfile = this.user.profileImage,
        username = this.user.username,
        likeCount = 25,
        commentCount = 15,
        image = ""
    )
}

fun List<Post>.toResponseList() : List<PostResponse>{
    return this.map { it.toResponse() }
}