package com.social.social.controller

import com.social.social.service.StoryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/story")
class StoryController(private val storyService: StoryService)
{

}