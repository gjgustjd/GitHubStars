package com.example.githubstars.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubstars.model.dto.UserItem

@Database(entities = [UserItem::class], version = 1)
abstract class LocalUserDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDAO

    companion object {
        private var databaseInstance: LocalUserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): LocalUserDatabase? {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalUserDatabase::class.java,
                    "user-database"
                ).build()
            }

            return databaseInstance
        }
    }
}