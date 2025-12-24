package com.social.social.controller

import com.social.social.dto.PostRequest
import com.social.social.dto.PostResponse
import com.social.social.dto.SearchRequest
import com.social.social.dto.StringMessage
import com.social.social.model.Post
import com.social.social.repository.PostRepository
import com.social.social.repository.UserRepository
import com.social.social.service.FileService
import com.social.social.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Paths

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService,
                     private val userRepository: UserRepository,
                     private val fileService: FileService) {


    @PostMapping("/upload" , consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])

    fun uploadPost(@RequestPart("image") image : MultipartFile,
                   @RequestPart("post") request : PostRequest,
    ) : PostResponse {

        val imageAddress = fileService.uploadFile(image)
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User Not Found") }

        val post = Post(
            user = user,
            image = imageAddress,
            caption = request.caption,
            date = request.date,
            time = request.time
        )

        return postService.addPost(post)
    }


    @GetMapping("/follower/{userId}/{lastSeenId}/{size}")
    fun getPostsByFollower(@PathVariable userId : Long,
                           @PathVariable lastSeenId : Long?,
                           @PathVariable size : Int) : List<PostResponse>
        = postService.getPostsByFollower(userId, lastSeenId, size)


    @GetMapping("/all/{userId}/{lastSeenId}/{size}")
    fun getPostsByUserid(@PathVariable userId : Long,
                         @PathVariable lastSeenId : Long?,
                         @PathVariable size : Int) : List<PostResponse>
        = postService.getPostsByUserid(userId, lastSeenId, size)


    @GetMapping("/search/{text}/{userId}/{lastSeenId}/{size}")
    fun searchPost(@PathVariable text: String,
                   @PathVariable userId : Long,
                   @PathVariable lastSeenId : Long?,
                   @PathVariable size : Int)
        = postService.searchPost(text, userId, lastSeenId, size)

    @GetMapping("/allposts/{userId}/{lastSeenId}/{size}")
    fun getAllPosts(@PathVariable userId : Long,
                    @PathVariable lastSeenId : Long?,
                    @PathVariable size : Int)
     = postService.getAllPosts(userId, lastSeenId, size)
}
