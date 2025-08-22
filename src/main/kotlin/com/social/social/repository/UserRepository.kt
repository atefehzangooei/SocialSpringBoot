package com.social.social.repository

import com.social.social.dto.StringMessage
import com.social.social.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User,Long>
{
    @Query(value = """
        SELECT COUNT(u) > 0
        FROM users u
        WHERE u.username = :username
    """, nativeQuery = true)
    fun existByUsername(@Param("username") username : String) : Boolean
}