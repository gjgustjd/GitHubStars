/*
* 외부 API로의 데이터 요청 및 내부 DB 연산을 주로 담당하는 Repository 클래스입니다.
* */

package com.example.githubstars.model

import com.example.githubstars.model.dto.SearchUserResponse
import com.example.githubstars.model.dto.UserItem
import com.example.githubstars.model.local.LocalUserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubStarsRepository @Inject constructor(
    private val db: LocalUserDatabase,
    private val retrofit: Retrofit
) {

    suspend fun getUsersList(
        search_string: String = "",
        page: Int = 1,
        per_page: Int = 100
    ): Response<SearchUserResponse> {
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

    fun getLocalUserList(word: String): Deferred<List<UserItem>> =
        CoroutineScope(Dispatchers.IO).async {
            db.localUserDao().getLocalUserList("%${word}%")
        }

}