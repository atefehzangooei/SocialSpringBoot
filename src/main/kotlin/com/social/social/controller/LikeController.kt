package com.social.social.controller

import com.social.social.dto.LikeRequest
import com.social.social.model.Like
import com.social.social.repository.PostRepository
import com.social.social.repository.UserRepository
import com.social.social.service.LikeService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/likes")
class LikeController(private val likeService: LikeService,
                     private val postRepository : PostRepository,
                     private val userRepository : UserRepository) {
    @PostMapping
    fun likePost(@RequestBody request: LikeRequest): Like {
        val post = postRepository.findById(request.postId).orElseThrow { RuntimeException("Post Not Found") }
        val user = userRepository.findById(request.user_id).orElseThrow { RuntimeException("User Not Found") }

        val like = Like(
            post = post,
            user = user,
            date = request.date,
            time = request.time
        )
        return likeService.likePost(like)
    }

    @GetMapping
    fun getLikesByPostId(@RequestBody postId: Long): List<Like> = likeService.getLikeByPostId(postId)

    @DeleteMapping
    fun disLikePost(@RequestBody request: LikeRequest) = likeService.disLikePost(request.postId, request.user_id)
}