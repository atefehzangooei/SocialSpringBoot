package com.social.social.service

import com.social.social.dto.CommentReplay
import com.social.social.dto.CommentRequest
import com.social.social.dto.CommentResponse
import com.social.social.dto.StringMessage
import com.social.social.model.Comment
import com.social.social.repository.CommentRepository
import com.social.social.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository,
    private val postService: PostService,
    private val userRepository: UserRepository) {

    fun addComment(request: CommentRequest): CommentResponse {

        val post = postService.findPostEntityById(request.postId)
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("User not found") }
        val comment = Comment(
            post = post,
            user = user,
            comment = request.comment,
            date = request.date,
            time = request.time
        )
        val saved = commentRepository.save(comment)
        val commentResponse = CommentResponse(
         id = saved.id,
         userId = user.id,
         userProfile = user.profileImage,
         username = user.username,
         comment = request.comment,
         date = request.date,
         time = request.time,
         postId = request.postId
        )
        
        return commentResponse

    }

    fun deleteComment(commentId: Long) : StringMessage{
        return try{
            commentRepository.deleteById(commentId)
            StringMessage(success = true)
        }
        catch (ex : Exception) {
            StringMessage(success = false, message = "خطا در حذف")
        }

    }


    fun replayComment(request: CommentReplay): Comment {

        val post = postService.findPostEntityById(request.postId)
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("User not found") }
        val parent = commentRepository.findById(request.parentId).orElseThrow { RuntimeException("comment not found") }
        val comment = Comment(
            post = post,
            user = user,
            comment = request.comment,
            date = request.date,
            time = request.time,
            parent = parent
        )
        return commentRepository.save(comment)

    }

    fun getComments(postId: Long): List<CommentResponse> {
        return commentRepository.getComments(postId).map {
            CommentResponse(
                id = it.getId(),
                userId = it.getUserId(),
                userProfile = it.getUserProfile(),
                username = it.getUsername(),
                comment = it.getComment(),
                date = it.getDate(),
                time = it.getTime(),
                postId = it.getPostId()

            )
        }
    }
}