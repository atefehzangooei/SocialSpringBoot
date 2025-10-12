package com.social.social

import com.social.social.controller.PostController
import com.social.social.controller.StoryController
import com.social.social.dto.PostRequest
import com.social.social.model.User
import com.social.social.repository.PostRepository
import com.social.social.repository.StoryRepository
import com.social.social.repository.UserRepository
import com.social.social.service.StoryService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val userRepository : UserRepository,
    private val postRepository : PostRepository,
    private val postController: PostController,
    private val storyRepository: StoryRepository
) : CommandLineRunner
{
    override fun run(vararg args: String?) {

      /*  val user = User(username = "atefeh70", password = "1234", profileImage = "myimage.jpg",
            phone = "09175282874", link = "nnnnn", date = "14.4/2/10", time = "18:25", email = "mmmm",
        bio = "ggggggg")
       userRepository.save(user)

        println("new user saved!")

        val newPost = PostRequest(1, "new postfefef", "1404/03/10", "18:00")

        postController.addPost(newPost)

         val imageurl = "https://w0.peakpx.com/wallpaper/209/381/HD-wallpaper-background-abstract-1-jpg-design-colors-blue-thumbnail.jpg"
        storyRepository.addStaticStory(5, imageurl, "1404/03/12", "20:00")
        */
      // userRepository.updateProfile()
    }
}