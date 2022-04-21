package com.example.helloworld.api

import com.example.helloworld.data.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("/posts/1")
    suspend fun getPost(): Response<Post>
}