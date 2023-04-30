package com.example.githubstars.model

import com.example.githubstars.model.dto.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchUsersAPI {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") search_word: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<SearchUserResponse>
}