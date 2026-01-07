package com.social.social.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date

@Service
class JwtService {

    //singniture key
    private val secretKey : Key =
        Keys.hmacShaKeyFor("my-super-secret-key-my-super-secret-key".toByteArray())

    //Tocken time
    private val expirationMillis = 60 * 60 * 1000

    //1. Create Jwt
    fun generateToken(userId : Long) : String{
        val now = Date()
        val expiryDate = Date(now.time + expirationMillis)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    //2. Extract userId from tocken
    fun extractUserId(token: String): Long {
        return getClaims(token).subject.toLong()
    }

    //3. Validation token
    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = getClaims(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    // get username
    fun extractUsername(token : String) : String {
        return getClaims(token).subject
    }

    //4. Read Claims
    private fun getClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

}