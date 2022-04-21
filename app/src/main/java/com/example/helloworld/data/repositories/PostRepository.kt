package com.example.helloworld.data.repositories

import com.example.helloworld.api.RetrofitInstance
import com.example.helloworld.data.models.Post
import retrofit2.Response

class PostRepository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}