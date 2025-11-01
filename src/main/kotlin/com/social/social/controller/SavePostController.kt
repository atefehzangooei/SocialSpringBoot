package com.social.social.controller

import com.social.social.dto.SavePostRequest
import com.social.social.dto.SavePostResponse
import com.social.social.dto.StringMessage
import com.social.social.model.Like
import com.social.social.model.SavePost
import com.social.social.repository.PostRepository
import com.social.social.repository.UserRepository
import com.social.social.service.SavePostService
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/save_post")
class SavePostController(private val savePostService: SavePostService,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository) {

    @PostMapping
    fun savePost(@RequestBody request : SavePostRequest) :StringMessage {
        val post = postRepository.findById(request.postId).orElseThrow { RuntimeException("Post Not Found") }
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("User Not Found") }

        val sp = SavePost(
            post = post,
            user = user,
            date = request.date,
            time = request.time
        )
        return try{
            savePostService.savePost(sp)
            StringMessage(success = true)
        }
        catch(e : Exception){
            StringMessage(success = false, message = e.toString())
        }
    }




    @DeleteMapping("/{postId}/{userId}")
    fun unSavePost(@PathVariable postId : Long,
                   @PathVariable userId : Long) : StringMessage
    = savePostService.unSavePost(postId, userId)

    @GetMapping("/{postId}")
    fun getSavePost(@PathVariable postId: Long) = savePostService.getSavePost(postId)
}