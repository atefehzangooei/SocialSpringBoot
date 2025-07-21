package com.social.social.controller

import com.social.social.dto.SavePostRequest
import com.social.social.model.SavePost
import com.social.social.service.SavePostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/save_post")
class SavePostController(private val savePostService: SavePostService) {

    @PostMapping
    fun savePost(@RequestBody request : SavePostRequest) : SavePost =
        savePostService.savePost(request)


    @DeleteMapping
    fun unSavePost(@PathVariable postId : Long,
                   @PathVariable userId : Long) = savePostService.unSavePost(postId, userId)
}