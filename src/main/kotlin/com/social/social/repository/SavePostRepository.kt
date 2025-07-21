package com.social.social.repository

import com.social.social.model.SavePost
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SavePostRepository : JpaRepository<SavePost, Long>
{
    @Query("""
        DELETE from save_post
        WHERE user_id = :userId
        AND post_id = :postId
    """, nativeQuery = true)
    fun unSavePost(@Param("postId") postId : Long,
                   @Param("userId") userId : Long)
}