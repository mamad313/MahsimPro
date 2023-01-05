package com.example.mahsimecommerce.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mahsimecommerce.data.AppDatabase
import com.example.mahsimecommerce.data.User
import com.example.mahsimecommerce.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel (application: Application): AndroidViewModel(application) {

    val readALlData: LiveData<List<User>>
    private val repository: RoomRepository
    init{
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = RoomRepository(userDao)
        readALlData = repository.readAllUser
    }

    fun addUser(user: User){
        viewModelScope.launch (Dispatchers.IO)  {
            repository.addUser(user)
        }
    }

    fun updateData(user: User){
        viewModelScope.launch (Dispatchers.IO)  {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch (Dispatchers.IO)  {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch (Dispatchers.IO)  {
            repository.deleteAllUser()
        }
    }
}