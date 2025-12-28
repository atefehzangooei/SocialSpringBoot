package com.social.social.controller

import com.social.social.dto.FollowRequest
import com.social.social.dto.StringMessage
import com.social.social.service.FollowerService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/follower")
class FollowerController(private val followerService: FollowerService) {

    @PostMapping("/follow")
    fun follow(@RequestBody request : FollowRequest) : StringMessage = followerService.follow(request)


    @DeleteMapping("/unfollow")
    fun unfollow(@PathVariable followerId : Long,
                 @PathVariable followingId : Long) : StringMessage = followerService.unfollow(followerId, followingId)


    @GetMapping("/follower/{userId}")
    fun getFollower(@PathVariable userId : Long) = followerService.getFollower(userId)

    @GetMapping("/following/{userId}")
    fun getFollowing(@PathVariable userId : Long) = followerService.getFollowing(userId)



}