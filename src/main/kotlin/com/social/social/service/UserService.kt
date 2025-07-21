package com.social.social.service

import com.social.social.model.User
import com.social.social.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers() : List<User> = userRepository.findAll()

    fun getUserById(id : Long) : User = userRepository.findById(id).orElse(null)

    fun createUser(user : User) : User = userRepository.save(user)

    fun deleteUser(id : Long) = userRepository.deleteById(id)

    fun updateUser(id : Long, newUser :User) : User?{
        return if(userRepository.existsById(id))
            userRepository.save(newUser)
        else
            null

    }

}