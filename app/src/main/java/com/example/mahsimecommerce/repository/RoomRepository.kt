package com.example.mahsimecommerce.repository


import androidx.lifecycle.LiveData
import com.example.mahsimecommerce.data.User
import com.example.mahsimecommerce.data.UserDao

class RoomRepository(private val userDao: UserDao) {

    val readAllUser: LiveData<List<User>> = userDao.getAllUsers()

    fun addUser(user: User){
        userDao.insert(user)
    }

    fun updateUser(user: User){
        userDao.update(user)
    }

    fun deleteUser(user: User){
        userDao.delete(user)
    }

    fun deleteAllUser(){
        userDao.deleteAll()
    }

}