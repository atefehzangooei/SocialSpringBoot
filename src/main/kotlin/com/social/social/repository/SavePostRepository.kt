package com.social.social.repository

import com.social.social.dto.SavePostResponse
import com.social.social.entity.SavePost
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SavePostRepository : JpaRepository<SavePost, Long>
{

    @Transactional
    @Modifying
    @Query(value = """
        DELETE from save_post
        WHERE user_id = :userId
        AND post_id = :postId
    """, nativeQuery = true)
    fun unSavePost(@Param("postId") postId : Long,
                   @Param("userId") userId : Long)



    @Query(value = """
        SELECT id, user_id, post_id, date, time FROM save_post
        WHERE post_id = :postId
    """, nativeQuery = true)
    fun getSavePost(@Param("postId") postId: Long) : List<SavePostResponse>


}