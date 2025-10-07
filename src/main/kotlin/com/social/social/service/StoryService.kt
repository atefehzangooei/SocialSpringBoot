package com.social.social.service

import com.social.social.dto.StoryRequest
import com.social.social.dto.StoryResponse
import com.social.social.dto.StringMessage
import com.social.social.model.LIFETIME
import com.social.social.model.Story
import com.social.social.repository.StoryRepository
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class StoryService(private val storyRepository: StoryRepository,
    private val userRepository: UserRepository,
    private val fileService: FileService) {

    fun addStory(userId: Long,
                 imageFile : MultipartFile,
                 date : String,
                 time : String): StoryResponse {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("no exist") }
        val userInfo = userRepository.getUserInfo(userId)
        val uploadedImage = fileService.uploadFile(imageFile)
        val story = Story(
            user = user,
            image = uploadedImage,
            date = date,
            time = time,
            lifetime = LIFETIME
        )
        storyRepository.save(story)

        return StoryResponse(
            userId = userId,
            profileImage = userInfo.getProfileImage(),
            username = userInfo.getUsername(),
            image = uploadedImage,
            date = date,
            time = time
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
        return  storyRepository.getStoryByUserid(userId).map {
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