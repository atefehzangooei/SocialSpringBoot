package com.social.social.service

import com.social.social.dto.SavePostRequest
import com.social.social.dto.SavePostResponse
import com.social.social.model.SavePost
import com.social.social.repository.SavePostRepository
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SavePostService(private val savePostRepository: SavePostRepository,
    private val userRepository: UserRepository,
    private val postService: PostService) {

    fun savePost(request: SavePost) = savePostRepository.save(request)

    fun unSavePost(postId : Long, userId : Long) = savePostRepository.unSavePost(postId, userId)


    fun getSavePost(postId : Long) = savePostRepository.getSavePost(postId)


}