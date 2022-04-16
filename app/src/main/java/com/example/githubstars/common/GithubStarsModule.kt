package com.example.githubstars.common

import android.content.Context
import androidx.room.Room
import com.example.githubstars.model.local.LocalUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
class GithubStarsModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context:Context):LocalUserDatabase
    {
        return Room.databaseBuilder(context,LocalUserDatabase::class.java,"user-db").build()
    }
}