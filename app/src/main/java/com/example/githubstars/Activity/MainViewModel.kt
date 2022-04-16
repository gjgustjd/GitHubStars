package com.example.githubstars.Activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubstars.model.GithubStarsRepository
import com.example.githubstars.model.dto.UserItem

class MainViewModel : ViewModel() {
    var userList = MutableLiveData<List<UserItem>>()

    fun setupUserList() {
        var response = GithubStarsRepository.getUsersList()
        if (response.isSuccessful) {
            userList.value = response.body()!!.userItems
        }
    }
}