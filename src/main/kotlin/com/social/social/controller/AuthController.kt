package com.social.social.controller

import com.social.social.dto.SigninRequest
import com.social.social.dto.SigninResponse
import com.social.social.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/signin")
    fun login(@RequestBody request: SigninRequest): SigninResponse{
        return authService.signin(request)
    }
}