package com.example.githubstars.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.githubstars.model.dto.SearchUserResponse
import com.example.githubstars.model.dto.UserItem
import com.example.githubstars.model.local.LocalUserDatabase
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubStarsRepository @Inject constructor(private val db: LocalUserDatabase) {

    suspend fun getUsersList(
        search_string: String = "d",
        page: Int = 1,
        per_page: Int = 100
    ): Response<SearchUserResponse> {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(SearchUsersAPI::class.java)
        return api.getUsers(search_string, page, per_page)
    }

    fun insertLocalUser(userItem: UserItem) {
        db.localUserDao().insert(userItem)
    }

    fun deleteLocalUser(userItem: UserItem) {
        db.localUserDao().delete(userItem)
    }

    fun getAllUserIdList() = flow {
        db.localUserDao().getAllUserIds().collect {
            emit(it)
        }
    }

    fun getLocalUserList(word: String) = flow {
        db.localUserDao().getLocalUserList("%${word}%").collect {
            emit(it)
        }
    }

}