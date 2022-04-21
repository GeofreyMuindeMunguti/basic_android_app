package com.example.helloworld.data.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.helloworld.data.db.UserDatabase
import com.example.helloworld.data.models.Post
import com.example.helloworld.data.models.User
import com.example.helloworld.data.repositories.PostRepository
import com.example.helloworld.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    val posts: MutableLiveData<Post> = MutableLiveData()
    private val user_repository: UserRepository
    private val post_repository: PostRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        user_repository = UserRepository(userDao)
        post_repository = PostRepository()
        readAllData = user_repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            user_repository.addUser(user)
        }
    }

    fun getPost() {
        viewModelScope.launch {
            val response: Response<Post> = post_repository.getPost()
            if (response.isSuccessful) {
                posts.value = response.body()
            }else{
                Log.d("API","Could not load posts")
            }
        }
    }
}