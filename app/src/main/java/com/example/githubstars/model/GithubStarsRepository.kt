package com.example.githubstars.model

import android.content.Context
import com.example.githubstars.model.dto.SearchUserResponse
import com.example.githubstars.model.dto.UserItem
import com.example.githubstars.model.local.LocalUserDatabase
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class GithubStarsRepository @Inject constructor(@ApplicationContext context: Context) {

    @Inject
    private lateinit var db: LocalUserDatabase

    suspend fun getUsersList(page: Int = 1, per_page: Int = 100): Response<SearchUserResponse> {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://developer.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(SearchUsersAPI::class.java)
        return api.getUsers(page, per_page)
    }

    suspend fun insertLocalUser(userItem: UserItem) {
        db.localUserDao().insert(userItem)
    }

    suspend fun deleteLocalUser(userItem:UserItem){
        db.localUserDao().delete(userItem)
    }

    suspend fun getAllUsers():List<UserItem>
    {
        return db.localUserDao().getAll()
    }
}