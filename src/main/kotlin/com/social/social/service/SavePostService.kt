package com.social.social.service

import com.social.social.dto.SavePostRequest
import com.social.social.model.SavePost
import com.social.social.repository.SavePostRepository
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SavePostService(private val savePostRepository: SavePostRepository,
    private val userRepository: UserRepository,
    private val postService: PostService) {

    fun savePost(request: SavePostRequest): SavePost {
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("user not found") }
        val post = postService.findPostEntityById(request.postId)
        val savePost = SavePost(
            user = user,
            post = post,
            date = request.date,
            time = request.time
        )
        return savePost
    }

    fun unSavePost(postId : Long, userId : Long) = savePostRepository.unSavePost(postId, userId)




}