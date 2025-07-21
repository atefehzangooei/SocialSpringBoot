package com.social.social.controller

import com.social.social.dto.FollowRequest
import com.social.social.model.Follower
import com.social.social.service.FollowerService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/follower")
class FollowerController(private val followerService: FollowerService) {

    @PostMapping("/follow")
    fun follow(@RequestBody request : FollowRequest) : Follower = followerService.follow(request)

    @DeleteMapping("/unfollow")
    fun unfollow(@PathVariable followerId : Long,
                 @PathVariable followingId : Long) = followerService.unfollow(followerId, followingId)


}