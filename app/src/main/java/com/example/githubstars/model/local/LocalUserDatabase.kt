package com.example.githubstars.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubstars.model.dto.UserItem
import javax.inject.Singleton

@Database(entities = [UserItem::class], version = 1)
abstract class LocalUserDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDAO
}