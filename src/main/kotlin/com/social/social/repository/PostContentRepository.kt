package com.social.social.repository

import com.social.social.model.PostContent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostContentRepository : JpaRepository<PostContent,Long>