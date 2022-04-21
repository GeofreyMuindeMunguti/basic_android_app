package com.example.helloworld.data.repositories

import androidx.lifecycle.LiveData
import com.example.helloworld.data.daos.UserDao
import com.example.helloworld.data.models.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}