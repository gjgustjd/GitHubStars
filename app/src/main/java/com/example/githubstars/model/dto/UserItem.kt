/*
GitHub API로부터 수신한 데이터를 전달하기 위한 데이터 클래스입니다.
내부 RoomDB에 저장하기 위한 Entity 클래스 역할도 겸합니다.
* */
package com.example.githubstars.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserItem(
    @PrimaryKey val id: Int = 0,
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val score: Int,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
) {
}