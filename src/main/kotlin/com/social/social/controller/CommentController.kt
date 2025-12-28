package com.social.social.controller

import com.social.social.dto.CommentReplay
import com.social.social.dto.CommentRequest
import com.social.social.dto.CommentResponse
import com.social.social.dto.StringMessage
import com.social.social.entity.Comment
import com.social.social.repository.UserRepository
import com.social.social.service.CommentService
import com.social.social.service.PostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentController(private val commentService: CommentService,
    private val postService: PostService,
    private val userRepository: UserRepository) {

    @PostMapping("/add")
    fun addComment(@RequestBody request : CommentRequest) : CommentResponse {
        return commentService.addComment(request)
    }

    @PostMapping("/replay")
    fun replayComment(@RequestBody request : CommentReplay) : Comment {
        return commentService.replayComment(request)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable commentId : Long) : StringMessage =
        commentService.deleteComment(commentId)


    @GetMapping("/{postId}")
    fun getComments(@PathVariable postId : Long) = commentService.getComments(postId)

}
