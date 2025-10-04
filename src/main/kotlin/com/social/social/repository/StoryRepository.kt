package com.social.social.repository

import com.social.social.model.Story
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StoryRepository : JpaRepository<Story,Long>  {

}