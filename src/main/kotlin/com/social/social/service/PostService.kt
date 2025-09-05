package com.social.social.service

import com.social.social.dto.PostResponse
import com.social.social.dto.SearchRequest

import com.social.social.model.Post
import com.social.social.projection.PostProjection
import com.social.social.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {
    //  fun getPostsByFollowing(user_id : Long) : List<Post> = postRepository.GetPostByFollowing(user_id)

    fun addPost(post: Post): Post = postRepository.save(post)

    fun getPostsByFollower(userId : Long): List<PostResponse> {
        return postRepository.getPostsByFollower(userId).map {
            PostResponse(
                id = it.getId(),
                caption = it.getCaption(),
                date = it.getDate(),
                time = it.getTime(),
                userId = it.getUserid(),
                userProfile = it.getProfileImage(),
                username = it.getUsername(),
                likeCount = it.getLikecount(),
                commentCount = it.getCommentcount(),
                image = "https://bouqs.com/blog/wp-content/uploads/2022/03/shutterstock_260182148-min.jpg",
                isLike = it.getIsLike(),
                isSave = it.getIsSave()
            )
        }
        //return posts.toResponseList()
    }

    fun getPostsByUserid(userId : Long): List<PostResponse> {
        return postRepository.getPostsByUserid(userId).map {
            PostResponse(
                id = it.getId(),
                caption = it.getCaption(),
                date = it.getDate(),
                time = it.getTime(),
                userId = it.getUserid(),
                userProfile = it.getProfileImage(),
                username = it.getUsername(),
                likeCount = it.getLikecount(),
                commentCount = it.getCommentcount(),
                image = "https://bouqs.com/blog/wp-content/uploads/2022/03/shutterstock_260182148-min.jpg",
                isLike = it.getIsLike(),
                isSave = it.getIsSave()
            )
        }
        //return posts.toResponseList()
    }

   /* fun getPostById(postId: Long, response : Boolean): PostResponse {

        val post = postRepository.findById(postId).orElseThrow {
            RuntimeException("Post Not Found : $postId")
        }
            return PostResponse(
                    id = it.getId(),
                    caption = it.getCaption(),
                    date = it.getDate(),
                    time = it.getTime(),
                    userId = it.getUserid(),
                    userProfile = it.getProfileImage(),
                    username = it.getUsername(),
                    likeCount = it.getLikecount(),
                    commentCount = it.getCommentcount(),
                    image = "https://bouqs.com/blog/wp-content/uploads/2022/03/shutterstock_260182148-min.jpg",
                    isLike = it.getIsLike(),
                    isSave = it.getIsSave()
                )
            }
    }*/

    fun findPostEntityById(postId : Long) : Post{
        val post = postRepository.findById(postId).orElseThrow { RuntimeException("post not found") }
        return post
    }

    fun searchPost(text : String, userId : Long) : List<PostResponse> {
        return postRepository.searchPost(text, userId).map {
            PostResponse(
                id = it.getId(),
                caption = it.getCaption(),
                date = it.getDate(),
                time = it.getTime(),
                userId = it.getUserid(),
                userProfile = it.getProfileImage(),
                username = it.getUsername(),
                likeCount = it.getLikecount(),
                commentCount = it.getCommentcount(),
                image = "",
                isLike = it.getIsLike(),
                isSave = it.getIsSave()
            )
        }
    }
}
