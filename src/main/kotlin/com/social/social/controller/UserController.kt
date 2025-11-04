package com.social.social.controller

import com.social.social.dto.*
import com.social.social.model.User
import com.social.social.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {


    @GetMapping
    fun getAllUsers() : List<User> = userService.getAllUsers()

    @GetMapping("/{userId}")
    fun getUserInfo(@PathVariable userId : Long) = userService.getUserInfo(userId)

    @PostMapping
    fun createUser(@RequestBody user : User) : User = userService.createUser(user)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id : Long) = userService.deleteUser(id)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id : Long, @RequestBody newUser : User) : ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, newUser)
        return if(updatedUser != null)
            ResponseEntity.ok(updatedUser)
        else
            ResponseEntity.notFound().build()

    }

    @PostMapping("/signup")
    fun signUp(@RequestBody request : SignupRequest) : StringMessage{
        return userService.signUp(request)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request : SigninRequest) : ResponseEntity<SigninResponse>{
        return userService.signIn(request)
    }

    @PostMapping("/forgetpassword")
    fun forgetPassword(@RequestBody request : ForgetRequest) : StringMessage{
        return userService.forgetPssword(request)
    }


}