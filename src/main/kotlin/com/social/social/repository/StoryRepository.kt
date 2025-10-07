package com.social.social.repository

import com.social.social.model.Story
import com.social.social.projection.StoryProjection
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StoryRepository : JpaRepository<Story,Long>  {

    //error query
    @Query("""
        SELECT
         story.user_id as userId,
         story.image as image,
         story.date as date,
         story.time as time,
         users.username as username,
         users.profile_image as profileImage
         FROM story 
         INNER JOIN users
         ON story.user_id = users.id 
         INNER JOIN followers
         ON followers.following_id = users.id
         WHERE (followers.follower_id = :userId) OR (story.user_id = :userId)
         GROUP BY story.user_id, story.date, story.time
    """, nativeQuery = true)
    fun getStoryOfFollowers(@Param("userId") userId : Long) : List<StoryProjection>


    @Query("""
        SELECT
         story.user_id as userId,
         story.image as image,
         story.date as date,
         story.time as time,
         users.username as username,
         users.profile_image as profileImage
         FROM story INNER JOIN users
         ON story.user_id = users.id
         WHERE story.user_id = :userId
    """, nativeQuery = true)
    fun getStoryByUserid(@Param("userId") userId : Long) : List<StoryProjection>


    @Modifying
    @Query("""
        INSERT INTO story(user_id, image, date, time) VALUES (:userId, :image, :date, :time)
    """, nativeQuery = true)
    @Transactional
    fun addStaticStory(@Param("userId") userId : Long,
                       @Param("image") image : String,
                       @Param("date") date : String,
                       @Param("time") time : String)
}