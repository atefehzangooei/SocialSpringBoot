package com.social.social.controller

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.dto.StringMessage
import com.social.social.service.StoryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/story")
class StoryController(private val storyService: StoryService)
{
    
    @PostMapping
    fun addStory(@RequestBody request : StoryRequest) = storyService.addStory(request)


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