package com.example.githubstars.Activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubstars.databinding.ActivityMainBinding
import com.example.githubstars.model.dto.UserItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var userList: List<UserItem>
    private lateinit var recycler_users: RecyclerView
    private lateinit var edt_search: EditText
    private lateinit var btn_search: AppCompatButton
    private lateinit var btn_tab_api: AppCompatButton
    private lateinit var btn_tab_local: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
    }

    private fun initializeView() {
        recycler_users = binding.recyclerItems
        edt_search = binding.edtSearch
        btn_tab_api = binding.btnTabApi
        btn_tab_local = binding.btnTabLocal
        initializeButtonSearch()
    }

    private fun initializeButtonSearch()
    {
        btn_search = binding.btnSearch
        btn_search.setOnClickListener{
            if(edt_search.text.isBlank())
                recycler_users.adapter = RecyclerAPIUsersAdapter(listOf())
            else {
                viewModel.setupUserList(edt_search.text.toString())
                viewModel.userList.observe(this) {
                    userList = it
                    setupRecycler()
                }
            }
        }
    }

    private fun setupRecycler() {
        recycler_users.adapter = RecyclerAPIUsersAdapter(userList)
        recycler_users.layoutManager = LinearLayoutManager(this)
    }

}