package com.social.social.service

import com.social.social.dto.FollowRequest
import com.social.social.dto.FollowerResponse
import com.social.social.model.Follower
import com.social.social.repository.FollowerRepository
import org.springframework.stereotype.Service


@Service
class FollowerService(private val followerRepository: FollowerRepository,
    private val userService: UserService) {

    fun follow(request : FollowRequest) {
        val followerUser = userService.getUserById(request.followerId)
        val followingUser = userService.getUserById(request.followingId)
        val newFollower = Follower(
            follower = followerUser,
            following = followingUser,
            date = request.date,
            time = request.time
        )
        followerRepository.save(newFollower)
    }

    fun unfollow(followerId : Long, followingId : Long) = followerRepository.unfollow(followerId, followingId)


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