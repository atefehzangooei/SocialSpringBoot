package com.social.social.controller

import com.social.social.dto.PostRequest
import com.social.social.dto.PostResponse
import com.social.social.dto.SearchRequest
import com.social.social.model.Post
import com.social.social.repository.UserRepository
import com.social.social.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService, private val userRepository: UserRepository) {

    /*
    @PostMapping("/upload" , consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])

    fun uploadPost(@RequestPart("neveshtak") neveshtak : String,
                   @RequestPart("image") image : MultipartFile) : ResponseEntity<String>{
       // val imagePath = "/images/${image.originalFilename}"

      //upload image
     //   val uploadDir = "/images/"
        val uploadDir = "D:\\D\\Kotlin\\Project\\Social\\Spring Boot\\social\\images\\"
        val path = File(uploadDir)
        if (!path.exists()) {
            path.mkdirs()  // اگر پوشه وجود ندارد، آن را بسازید
        }
        try {
            image.transferTo(File(uploadDir + image.originalFilename))

            return ResponseEntity("پست جدید با موفقیت آپلود شد", HttpStatus.OK)

        }
        catch(e: Exception){
            println("error is : ${e.message}")

            return ResponseEntity("خطا در آپلود فایل", HttpStatus.INTERNAL_SERVER_ERROR)

        }

        //val newPost = Post(i)

    }
    */

    @PostMapping
    fun addPost(@RequestBody request : PostRequest) : Post{
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User Not Found") }

        val post = Post(
            user = user,
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
}
