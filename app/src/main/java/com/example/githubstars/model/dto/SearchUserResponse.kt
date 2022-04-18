/*
GitHubAPI로부터 받은 데이터를 전달하기 위한 클래스입니다.
* */
package com.example.githubstars.model.dto

data class SearchUserResponse(
    val incomplete_results: Boolean,
    val items: List<UserItem>,
    val total_count: Int
)