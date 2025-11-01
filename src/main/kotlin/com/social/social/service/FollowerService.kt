package com.social.social.service

import com.social.social.dto.FollowRequest
import com.social.social.dto.FollowerResponse
import com.social.social.dto.StringMessage
import com.social.social.model.Follower
import com.social.social.repository.FollowerRepository
import org.springframework.stereotype.Service


@Service
class FollowerService(private val followerRepository: FollowerRepository,
    private val userService: UserService) {

    fun follow(request: FollowRequest): StringMessage {
        val followerUser = userService.getUserById(request.followerId)
        val followingUser = userService.getUserById(request.followingId)
        val newFollower = Follower(
            follower = followerUser,
            following = followingUser,
            date = request.date,
            time = request.time
        )
      return  try {
            followerRepository.save(newFollower)
            StringMessage(success = true)
        } catch (ex: Exception) {
            StringMessage(success = false, message = ex.toString())
        }
    }

    fun unfollow(followerId: Long, followingId: Long): StringMessage {
       return try {
            followerRepository.unfollow(followerId, followingId)
            StringMessage(success = true)
        }
        catch(ex : Exception){
            StringMessage(success = false, message = ex.toString())
        }
    }



    fun getFollower(userId : Long) : List<FollowerResponse>{
       return followerRepository.getFollower(userId).map {
           FollowerResponse(
               userid = it.getUserid(),
               username = it.getUsername(),
               profileImage = it.getProfileImage()
           )
       }

    }

    fun getFollowing(userId : Long) : List<FollowerResponse>{
       return followerRepository.getFollowing(userId).map {
           FollowerResponse(
               userid = it.getUserid(),
               username = it.getUsername(),
               profileImage = it.getProfileImage()
           )
       }

    }
}