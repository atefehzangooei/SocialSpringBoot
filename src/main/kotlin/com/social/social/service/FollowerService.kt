package com.social.social.service

import com.social.social.dto.FollowRequest
import com.social.social.model.Follower
import com.social.social.repository.FollowerRepository
import org.springframework.stereotype.Service


@Service
class FollowerService(private val followerRepository: FollowerRepository,
    private val userService: UserService) {

    fun follow(request : FollowRequest) : Follower {
        val followerUser = userService.getUserById(request.followerId)
        val followingUser = userService.getUserById(request.followingId)
        val newFollower = Follower(
            follower = followerUser,
            following = followingUser,
            date = request.date,
            time = request.time
        )
        return newFollower
    }

    fun unfollow(followerId : Long, followingId : Long) = followerRepository.unfollow(followerId, followingId)

}