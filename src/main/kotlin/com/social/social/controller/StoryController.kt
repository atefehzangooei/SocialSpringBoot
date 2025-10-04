package com.social.social.controller

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.service.StoryService
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


}