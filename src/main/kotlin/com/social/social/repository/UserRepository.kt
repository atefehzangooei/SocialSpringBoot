package com.social.social.repository

import com.social.social.dto.SigninResponse
import com.social.social.dto.StringMessage
import com.social.social.model.User
import com.social.social.projection.SigninProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User,Long>
{
    @Query(value = """
        SELECT COUNT(id) > 0
        FROM users
        WHERE username = :username
    """, nativeQuery = true)
    fun existByUsername(@Param("username") username : String) : Boolean


    @Query(value = """
        SELECT 
        users.id as id,
        users.username as username,
        users.phone as phone,
        users.password as password,
        users.link as link,
        users.date as date,
        users.time as time,
        users.bio as bio,
        users.email as email,
        users.profile_image as profile_image  
        FROM users
        WHERE username = :username 
        AND password = :password
    """, nativeQuery = true)
    fun signIn(@Param("username") username : String,
               @Param("password") password : String) : SigninProjection?

}