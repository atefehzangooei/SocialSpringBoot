package com.social.social.service

import com.social.social.dto.SignupRequest
import com.social.social.dto.StringMessage
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

    fun signUp(request : SignupRequest) : StringMessage{
        if(userRepository.existByUsername(request.username)){
            return StringMessage("repeated username")
        }
        else{
            val newUser = User(
                username = request.username,
                password = request.password,
                phone = request.phone,
                link = "",
                date = "",
                time = "",
                profileImage = "",
                email = "",
                bio = ""
            )
            userRepository.save(newUser)
            return StringMessage("success")
        }
    }

}