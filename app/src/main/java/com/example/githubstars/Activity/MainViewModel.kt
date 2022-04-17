package com.example.githubstars.Activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubstars.model.GithubStarsRepository
import com.example.githubstars.model.dto.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: GithubStarsRepository
    var userList = MutableLiveData<List<UserItem>>()

    fun setupUserList(word: String="") {
        viewModelScope.launch {
            var response = repository.getUsersList(search_string = word)
            if (response.isSuccessful) {
                userList.value = response.body()!!.items
            }
        }
    }
}