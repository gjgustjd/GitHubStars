package com.example.githubstars.model.dto

data class SearchUserResponse(
    val incomplete_results: Boolean,
    val items: List<UserItem>,
    val total_count: Int
)