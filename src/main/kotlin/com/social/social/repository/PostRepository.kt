package com.social.social.repository

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
          COUNT(comment.post_id) as commentcount,
          users.username as username,
          users.profile_image as profileImage,
          CASE WHEN EXISTS (
        SELECT 1 
        FROM likes  
        WHERE likes.post_id = post.id AND likes.user_id = :userId
    ) THEN true ELSE false END AS isLike,
    
    CASE WHEN EXISTS (
        SELECT 1 
        FROM save_post  
        WHERE save_post.post_id = post.id AND save_post.user_id = :userId
    ) THEN true ELSE false END AS isSave
    
        FROM post
        LEFT JOIN users
        ON post.user_id = users.id
        LEFT JOIN likes
        ON post.id = likes.post_id
        INNER JOIN comment
        ON post.id = comment.post_id
        
        WHERE post.user_id IN (
        SELECT following_id 
        FROM followers
        WHERE follower_id = :userId
        )
        GROUP BY post.id, post.user_id, post.caption, post.date, post.time, users.username, users.profile_image
        ORDER BY post.id DESC
    """, nativeQuery = true)
    fun getPostsByFollower(@Param("userId") userId : Long) : List<PostProjection>



    @Query("""
        SELECT
          post.id as id,
          post.user_id as userid,
          post.caption as caption,
          post.date as date, 
          post.time as time,
          COUNT(DISTINCT likes.post_id) as likecount,
          COUNT(comment.post_id) as commentcount,
          users.username as username,
          users.profile_image as profileImage,
          CASE WHEN EXISTS (
        SELECT 1 
        FROM likes  
        WHERE likes.post_id = post.id AND likes.user_id = :userId
    ) THEN true ELSE false END AS isLike,
    
    CASE WHEN EXISTS (
        SELECT 1 
        FROM save_post  
        WHERE save_post.post_id = post.id AND save_post.user_id = :userId
    ) THEN true ELSE false END AS isSave
    
        FROM users
        INNER JOIN post
        ON users.id = post.user_id
        LEFT JOIN likes
        ON post.id = likes.post_id
        INNER JOIN comment
        ON post.id = comment.post_id
        WHERE post.user_id = :userId
        GROUP BY post.id, post.user_id, post.caption, post.date, post.time, users.username, users.profile_image
        ORDER BY post.id DESC
    """, nativeQuery = true)
    fun getPostsByUserid(@Param("userId") userId : Long) : List<PostProjection>

}