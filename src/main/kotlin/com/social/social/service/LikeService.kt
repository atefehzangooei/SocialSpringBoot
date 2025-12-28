package com.social.social.service

import com.social.social.dto.StringMessage
import com.social.social.entity.Like
import com.social.social.repository.LikeRepository
import org.springframework.stereotype.Service

@Service
class LikeService(private val likeRepository: LikeRepository) {

    fun likePost(like : Like) : Like = likeRepository.save(like)

    fun getLikeByPostId(postId : Long) : List<Like> = likeRepository.getLikesByPostId(postId)

    fun disLikePost(postId: Long, user_id : Long) : StringMessage {
        return try{
            likeRepository.disLikePost(postId, user_id)
            StringMessage(success = true)
        }
        catch(ex : Exception){
            StringMessage(success = false, message = ex.toString())
        }
    }
}