package com.social.social.controller

import com.social.social.dto.LikeRequest
import com.social.social.dto.StringMessage
import com.social.social.model.Like
import com.social.social.repository.PostRepository
import com.social.social.repository.UserRepository
import com.social.social.service.LikeService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun likePost(@RequestBody request: LikeRequest): StringMessage {
        val post = postRepository.findById(request.postId).orElseThrow { RuntimeException("Post Not Found") }
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("User Not Found") }

        val like = Like(
            post = post,
            user = user,
            date = request.date,
            time = request.time
        )
        return try{
            likeService.likePost(like)
            StringMessage(("yes"))
        }
        catch(e : Exception){
            StringMessage(("no"))
        }
    }

    @GetMapping("/{id}")
    fun getLikesByPostId(@PathVariable id: Long): List<Like> = likeService.getLikeByPostId(id)

    @DeleteMapping("/dislike/{postId}/{userId}")
    fun disLikePost(@PathVariable postId: Long,
                    @PathVariable userId : Long) = likeService.disLikePost(postId, userId)
}