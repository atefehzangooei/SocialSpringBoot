package com.social.social.controller

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.dto.StringMessage
import com.social.social.service.StoryService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/story")
class StoryController(private val storyService: StoryService)
{
    
    @PostMapping("/add", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun addStory(@RequestPart("imageFile") imageFile : MultipartFile,
                 @RequestPart("userId") userId : Long,
                 @RequestPart("date") date : String,
                 @RequestPart("time") time : String) =
        storyService.addStory(userId, imageFile, date, time)


    @DeleteMapping("/{storyId}")
    fun deleteStory(@PathVariable("storyId") storyId : Long) : StringMessage =
        storyService.deleteStory(storyId)

    @GetMapping("/followers/{userId}")
    fun getStoryOfFollowers(@PathVariable("userId") userId : Long) =
        storyService.getStoryOfFollowers(userId)

    @GetMapping("user/{userId}")
    fun getStoryByUserid(@PathVariable("userId") userId : Long)=
        storyService.getStoryByUserid(userId)
}