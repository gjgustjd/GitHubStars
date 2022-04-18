package com.example.githubstars.model.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.githubstars.model.dto.UserItem
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull


@Dao
interface LocalUserDAO {
    @Insert
    fun insert(item: UserItem)

    @Update
    fun update(item: UserItem)

    @Delete
    fun delete(item: UserItem)

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<UserItem>>

    @Query("SELECT id FROM users")
    fun getAllUserIds(): Flow<List<Int>>

    @Query("SELECT * FROM users WHERE login LIKE :word")
    fun getLocalUserList(word: String): Flow<List<UserItem>>
}