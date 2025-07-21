package com.social.social.repository

import com.social.social.model.Follower
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
}
