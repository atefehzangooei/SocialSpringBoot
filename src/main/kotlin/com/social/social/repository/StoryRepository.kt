package com.social.social.repository

import com.social.social.model.Story
import com.social.social.projection.StoryProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StoryRepository : JpaRepository<Story,Long>  {

    //error query
    @Query("""
        SELECT
         story.user_id as userId,
         story.file as image,
         story.date as date,
         story.time as time,
         users.username as username,
         users.profile_image as profileImage
         FROM story INNER JOIN users
         ON story.user_id = users.id
         WHERE story.user_id = :userId
    """, nativeQuery = true)
    fun getStoryOfFollowers(@Param("userId") userId : Long) : List<StoryProjection>


    @Query("""
        SELECT
         story.user_id as userId,
         story.file as image,
         story.date as date,
         story.time as time,
         users.username as username,
         users.profile_image as profileImage
         FROM story INNER JOIN users
         ON story.user_id = users.id
         WHERE story.user_id = :userId
    """, nativeQuery = true)
    fun getStoryByUserid(@Param("userId") userId : Long) : List<StoryProjection>
}