package com.example.mahsimecommerce.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // for don't add same user
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("select * from user_table order by id ASC")
    fun getAllUsers(): LiveData<List<User>>
}