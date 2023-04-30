/*
RoomDatabase에 의존성을 주입할 용도로 만들어진 공통 Module 클래스입니다.
*/
package com.example.githubstars.common

import android.content.Context
import androidx.room.Room
import com.example.githubstars.model.local.LocalUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
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

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun getItemDecorator(@ActivityContext context: Context): UserItemDecorator =
        UserItemDecorator(context)
}

