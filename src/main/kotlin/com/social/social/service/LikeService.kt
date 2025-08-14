package com.social.social.service

import com.social.social.dto.StringMessage
import com.social.social.model.Like
import com.social.social.repository.LikeRepository
import org.springframework.stereotype.Service

@Service
class LikeService(private val likeRepository: LikeRepository) {

    fun likePost(like : Like) : Like = likeRepository.save(like)

    fun getLikeByPostId(postId : Long) : List<Like> = likeRepository.getLikesByPostId(postId)

    fun disLikePost(postId: Long, user_id : Long) = likeRepository.disLikePost(postId, user_id)
}