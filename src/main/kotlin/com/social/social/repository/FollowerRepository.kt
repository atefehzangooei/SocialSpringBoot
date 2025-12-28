package com.social.social.repository

import com.social.social.entity.Follower
import com.social.social.projection.FollowerProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FollowerRepository : JpaRepository<Follower, Long>
{
    @Query("""
        DELETE from followers 
        WHERE follower_id = :followerId
        AND following_id = :followingId
    """, nativeQuery = true)
    fun unfollow(@Param("followerId") followerId : Long,
                 @Param("followingId") followingId : Long)


    @Query(value = """
        SELECT 
        followers.follower_id as userid,
        users.username as username,
        users.profile_image as profileImage
        FROM followers 
        INNER JOIN users
        ON followers.follower_id = users.id
        WHERE followers.following_id = :userId

    """, nativeQuery = true)
    fun getFollower(@Param("userId") userId : Long) : List<FollowerProjection>


    @Query(value = """
        SELECT 
        followers.following_id as userid,
        users.username as username,
        users.profile_image as profileImage
        FROM followers 
        INNER JOIN users
        ON followers.following_id = users.id
        WHERE followers.follower_id = :userId

    """, nativeQuery = true)
    fun getFollowing(@Param("userId") userId : Long) : List<FollowerProjection>

}
