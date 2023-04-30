package com.example.githubstars.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubstars.model.dto.UserItem

@Database(entities = [UserItem::class], version = 1)
abstract class LocalUserDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDAO
}