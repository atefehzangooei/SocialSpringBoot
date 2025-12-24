package com.social.social.controller

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.dto.StringMessage
import com.social.social.model.Post
import com.social.social.model.Story
import com.social.social.repository.UserRepository
import com.social.social.service.FileService
import com.social.social.service.StoryService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/story")
class StoryController(
    private val storyService: StoryService,
    private val fileService: FileService,
    private val userRepository: UserRepository
    )
{

    @PostMapping("/upload" , consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadStory(@RequestPart("image") image : MultipartFile,
                 @RequestPart("story") request : StoryRequest
    ) : StoryResponse {
        val imageAddress = fileService.uploadFile(image)
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User Not Found") }

        val story = Story(
            user = user,
            image = imageAddress,
            date = request.date,
            time = request.time
        )

        return storyService.addStory(story)
    }


    @DeleteMapping("/delete/{storyId}")
    fun deleteStory(@PathVariable("storyId") storyId : Long) : StringMessage =
        storyService.deleteStory(storyId)

    @GetMapping("/followers/{userId}")
    fun getStoryOfFollowers(@PathVariable("userId") userId : Long) =
        storyService.getStoryOfFollowers(userId)

    @GetMapping("user/{userId}")
    fun getStoryByUserid(@PathVariable("userId") userId : Long) =
        storyService.getStoryByUserid(userId)


}