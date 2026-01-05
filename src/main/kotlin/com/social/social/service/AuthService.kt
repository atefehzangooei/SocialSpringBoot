package com.social.social.service

import com.social.social.dto.SigninRequest
import com.social.social.dto.SigninResponse
import com.social.social.dto.TokenResponse
import com.social.social.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun login(request: SigninRequest): SigninResponse {
        val user = userRepository.findByUsername(request.username)
            ?: throw RuntimeException("User not found")

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("Invalid credentials")
        }

        val token = jwtService.generateToken(user.id)

        return TokenResponse(token)
    }
}