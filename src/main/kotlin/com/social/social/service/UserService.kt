package com.social.social.service

import com.social.social.dto.*
import com.social.social.model.User
import com.social.social.repository.UserRepository
import org.apache.logging.log4j.message.StringFormattedMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
            return StringMessage(success = false,
                message = "repeated username")
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
            return StringMessage(success = true)
        }
    }

    fun signIn(request: SigninRequest) : ResponseEntity<SigninResponse> {
        val user = userRepository.signIn(request.username, request.password)

        return if(user == null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
        else{
            val data = SigninResponse(
                id = user.getId(),
                username = user.getUsername(),
                password = user.getPassword(),
                phone = user.getPhone(),
                email = user.getEmail(),
                link = user.getLink(),
                bio = user.getBio(),
                date = user.getDate(),
                time = user.getTime(),
                profileImage = user.getProfileImage()
            )
            ResponseEntity.ok(data)
        }
    }

    fun forgetPssword(request : ForgetRequest) : StringMessage{
        return if(userRepository.existUserByPhoneUsername(request.username, request.phone)){
            StringMessage(success = true)
        }
        else{
            StringMessage(success = false, message = "no user")
        }
    }

    fun getUserInfo(userId : Long) : UserInfo{
        val userInfo = userRepository.getUserInfo(userId)
        return UserInfo(
            id = userInfo.getId(),
            username = userInfo.getUsername(),
            password = userInfo.getPassword(),
            email = userInfo.getEmail(),
            phone = userInfo.getPhone(),
            link = userInfo.getLink(),
            bio = userInfo.getBio(),
            profileImage = userInfo.getProfileImage(),
            follower = userInfo.getFollower(),
            following = userInfo.getFollowing(),
            postCount = userInfo.getPostCount()
            )
    }

}