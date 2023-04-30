package com.example.githubstars.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.githubstars.model.dto.UserItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalUserDAO {
    @Insert
    fun insert(item: UserItem)

    @Update
    fun update(item: UserItem)

    @Delete
    fun delete(item: UserItem)

    @Query("SELECT id FROM users")
    fun getAllUserIds(): Flow<List<Int>>

    @Query("SELECT * FROM users WHERE login LIKE :word")
    suspend fun getLocalUserList(word: String): List<UserItem>
}