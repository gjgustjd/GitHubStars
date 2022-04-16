package com.example.githubstars.common

import android.content.Context
import androidx.room.Room
import com.example.githubstars.model.local.LocalUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GithubStarsModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): LocalUserDatabase =
        Room.databaseBuilder(context, LocalUserDatabase::class.java, "user-db")
            .fallbackToDestructiveMigration().build()
}