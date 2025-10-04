package com.social.social.service

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.dto.StringMessage
import com.social.social.model.LIFETIME
import com.social.social.model.Story
import com.social.social.repository.StoryRepository
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class StoryService(private val storyRepository: StoryRepository,
    private val userRepository: UserRepository,
    private val fileService: FileService) {

    fun addStory(request: StoryRequest): StoryResponse {
        val user = userRepository.findById(request.userId).orElseThrow { RuntimeException("no exist") }
        val userInfo = userRepository.getUserInfo(request.userId)
        val uploadedImage = fileService.uploadFile(request.imageFile)
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
            profileImage = userInfo.getProfileImage(),
            username = userInfo.getUsername(),
            image = uploadedImage,
            date = request.date,
            time = request.time
        )
    }


    fun deleteStory(storyId: Long): StringMessage {
        return if (storyRepository.existsById(storyId)) {
            storyRepository.deleteById(storyId)
            StringMessage("success")
        } else {
            StringMessage("failure")
        }
    }

    fun getStoryOfFollowers(userId : Long) : List<StoryResponse> {
        return  storyRepository.getStoryOfFollowers(userId).map {
            StoryResponse(
                userId = it.getUserid(),
                profileImage = it.getProfileImage(),
                username = it.getUsername(),
                image = it.getImage(),
                date = it.getDate(),
                time = it.getTime()
            )
        }
    }

    fun getStoryByUserid(userId : Long) : List<StoryResponse> {
        return  storyRepository.getStoryOfFollowers(userId).map {
            StoryResponse(
                userId = it.getUserid(),
                profileImage = it.getProfileImage(),
                username = it.getUsername(),
                image = it.getImage(),
                date = it.getDate(),
                time = it.getTime()
            )
        }
    }
}