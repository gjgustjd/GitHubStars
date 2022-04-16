package com.example.githubstars.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.githubstars.model.dto.UserItem


@Dao
interface LocalUserDAO {
    @Insert
    fun insert(item: UserItem)

    @Update
    fun update(item: UserItem)

    @Delete
    fun delete(item: UserItem)
}