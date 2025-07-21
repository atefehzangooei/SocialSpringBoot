package com.social.social.repository

import com.social.social.model.Comment
import com.social.social.projection.CommentProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long>
{
    @Query(value = """
        SELECT 
        comment.id as id,
        comment.post_id as postId,
        comment.comment as comment,
        comment.date as date,
        comment.time as time,
        users.id as userId,
        users.username as username,
        users.profile_image as userProfile
        FROM comment 
        LEFT JOIN users
        ON comment.user_id = users.id
        WHERE comment.post_id = :postId
        ORDER BY comment.id DESC
     
    """
        , nativeQuery = true)
    fun getComments(@Param("postId") postId : Long) : List<CommentProjection>

}