package com.social.social.service

import com.social.social.dto.*
import com.social.social.entity.STORY_DURATION
import com.social.social.entity.Story
import com.social.social.repository.StoryRepository
import com.social.social.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StoryService(private val storyRepository: StoryRepository,
    private val userRepository: UserRepository,
    private val fileService: FileService) {

    private val logger : Logger = LoggerFactory.getLogger(PostService::class.java)


    fun addStory(story: Story): StoryResponse {

        logger.info("add post image = ${story.image}")
        try {
            val newPost = storyRepository.save(story)
        }
        catch (ex : Exception){
            logger.info("add post ex = ${ex.toString()}")
        }

        val newPost = storyRepository.save(story)

        return StoryResponse(
            userId = story.user.id,
            profileImage = story.user.profileImage,
            username = story.user.username,
            date = story.date,
            time = story.time,
            duration = STORY_DURATION
        )
    }

    fun deleteStory(storyId: Long): StringMessage {
        return if (storyRepository.existsById(storyId)) {
            storyRepository.deleteById(storyId)
            StringMessage(success = true)
        } else {
            StringMessage(success = false, message = "خطایی رخ داده است")
        }
    }

    fun getStoryOfFollowers(userId : Long) : List<StoryResponse> {
        return  storyRepository.getStoryOfFollowers(userId).map {
            StoryResponse(
                userId = it.getUserid(),
                profileImage = it.getProfileImage(),
                username = it.getUsername(),
                date = it.getDate(),
                time = it.getTime(),
                duration = STORY_DURATION
            )
        }
    }

    fun getStoryByUserid(userId : Long) : List<UserStory> {
        return  storyRepository.getStoryByUserid(userId).map {
            UserStory(
                profileImage = it.getProfileImage(),
                username = it.getUsername(),
                image =  BASE_URL + it.getImage(),
                date = it.getDate(),
                time = it.getTime(),
                duration = STORY_DURATION
            )
        }
    }
}