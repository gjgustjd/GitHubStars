package com.example.githubstars.model.dto

data class SearchUserResponse(
    val incomplete_results: Boolean,
    val userItems: List<UserItem>,
    val total_count: Int
)