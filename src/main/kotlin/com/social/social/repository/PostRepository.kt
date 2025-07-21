package com.social.social.repository

import com.social.social.dto.PostResponse
import com.social.social.model.Post
import com.social.social.projection.PostProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> //primary key ==> Long(Id)
{

    @Query("""
        SELECT
          post.id as id,
          post.user_id as userid,
          post.caption as caption,
          post.date as date, 
          post.time as time,
          COUNT(DISTINCT likes.post_id) as likecount,
          COUNT(DISTINCT comment.post_id) as commentcount,
          users.username as username,
          users.profile_image as profileImage
        FROM users
        INNER JOIN post
        ON users.id = post.user_id
        LEFT JOIN likes
        ON post.id = likes.post_id
        LEFT JOIN comment
        ON post.id = comment.post_id
        GROUP BY post.id, post.user_id, post.caption, post.date, post.time, users.username, users.profile_image
        ORDER BY post.id DESC
    """, nativeQuery = true)
    fun getAllPost() : List<PostProjection>


}