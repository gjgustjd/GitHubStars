package com.example.githubstars.model

import com.example.githubstars.model.dto.SearchUserResponse
import com.example.githubstars.model.dto.UserItem
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubStarsRepository {

    fun getUsersList(page: Int = 1, per_page: Int = 100): Response<SearchUserResponse>{
        var gson = GsonBuilder().setLenient().create()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://developer.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(SearchUsersAPI::class.java)
        return api.getUsers(page, per_page)
    }
}