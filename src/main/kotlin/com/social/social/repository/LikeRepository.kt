package com.social.social.repository

import com.social.social.dto.StringMessage
import com.social.social.model.Like
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LikeRepository : JpaRepository<Like, Long>
{
    @Query(value = """
        SELECT users.*, likes.post_id, likes.user_id FROM likes 
        INNER JOIN users ON users.id = likes.user_id
        WHERE likes.post_id = :postId
    """, nativeQuery = true)
    fun getLikesByPostId(@Param("postId") postId : Long) : List<Like>


    @Query(value = """
        DELETE FROM likes 
        WHERE user_id = :userId
        AND post_id = :postId
    """, nativeQuery = true)

    fun disLikePost(@Param("postId") postId : Long,
                    @Param("userId") userId : Long )
}