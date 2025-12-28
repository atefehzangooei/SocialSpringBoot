package com.social.social.service

import com.social.social.dto.StringMessage
import com.social.social.entity.SavePost
import com.social.social.repository.SavePostRepository
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SavePostService(private val savePostRepository: SavePostRepository,
    private val userRepository: UserRepository,
    private val postService: PostService) {

    fun savePost(request: SavePost) : StringMessage {
        return try {
            savePostRepository.save(request)
            StringMessage(success = true)
        }
        catch (ex : Exception){
            StringMessage(success = false, message = ex.toString())
        }
    }

    fun unSavePost(postId : Long, userId : Long) : StringMessage {
        return try {
            savePostRepository.unSavePost(postId, userId)
            StringMessage(success = true)
        }
        catch(ex : Exception){
            StringMessage(success = false, message = ex.toString())
        }
    }


    fun getSavePost(postId : Long) = savePostRepository.getSavePost(postId)


}