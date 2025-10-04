package com.social.social.service

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.model.LIFETIME
import com.social.social.model.Story
import com.social.social.repository.StoryRepository
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class StoryService(private val storyRepository: StoryRepository,
    private val userRepository: UserRepository)
{
    fun addStory(request : StoryRequest) : StoryResponse{
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("no exist")}
        val uploadedImage = ""
        val story = Story(
            user = user,
            image = uploadedImage,
            date = request.date,
            time = request.time,
            lifetime = LIFETIME
        )
        storyRepository.save(story)

        return StoryResponse(
            userId = request.userId,
            image = uploadedImage,
            date = request.date,
            time = request.time
        )
    }

}