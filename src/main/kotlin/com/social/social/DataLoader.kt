package com.social.social

import com.social.social.controller.PostController
import com.social.social.dto.PostRequest
import com.social.social.model.User
import com.social.social.repository.PostRepository
import com.social.social.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val userRepository : UserRepository,
    private val postRepository : PostRepository,
    private val postController: PostController
) : CommandLineRunner
{
    override fun run(vararg args: String?) {
        val user = User(username = "atefeh70", password = "1234", profileImage = "myimage.jpg",
            phone = "09175282874", link = "nnnnn", date = "14.4/2/10", time = "18:25", email = "mmmm",
        bio = "ggggggg")
       // userRepository.save(user)

        println("new user saved!")

        val newPost = PostRequest(1, "new postfefef", "1404/03/10", "18:00")
       //
        // postController.addPost(newPost)

        println("new post saved!")
    }
}