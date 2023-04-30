/*
* 메인 액티비티 클래스입니다.
* MainViewModel로부터 데이터를 관찰하여 Recycler를 업데이트하는 역할을 주로 담당합니다.
* 선택된 탭에 따라 RecyclerUsersAdapter에 다른 데이터를 넣어 업데이트합니다.
* */
package com.example.githubstars.Activity

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubstars.R
import com.example.githubstars.common.UserItemDecorator
import com.example.githubstars.databinding.ActivityMainBinding
import com.example.githubstars.model.dto.UserItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var userIdList: List<Int>? = null
    private var recyclerAdapter: RecyclerUsersAdapter? = null
    private var recyclerViewState: Parcelable? = null
    private lateinit var currentTab: AppCompatButton
    private lateinit var recycler_users: RecyclerView
    private lateinit var edt_search: EditText
    private lateinit var btn_search: AppCompatButton
    private lateinit var btn_tab_api: AppCompatButton
    private lateinit var btn_tab_local: AppCompatButton

    private var decorator:UserItemDecorator? = null

    @Inject
    fun setDecorator(decorator: UserItemDecorator) {
       this.decorator = decorator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLocalUserIdList()
        initializeView()
    }

    private fun setupLocalUserIdList() {
        viewModel.userIdList.observe(this)
        {
            userIdList = it
            recyclerAdapter?.let {
                setupRecycler(recyclerAdapter!!.userList)
            }
        }
    }

    private fun initializeView() {
        initializeBtnApi()
        initializeBtnLocal()
        initializeButtonSearch()

        currentTab = btn_tab_api
        recycler_users = binding.recyclerItems
        edt_search = binding.edtSearch
    }

    private fun initializeBtnApi() {
        btn_tab_api = binding.btnTabApi
        btn_tab_api.setOnClickListener {
            setCurrentTab(btn_tab_api)
        }
    }

    private fun initializeBtnLocal() {
        btn_tab_local = binding.btnTabLocal
        btn_tab_local.setOnClickListener {
            setCurrentTab(btn_tab_local)
        }
    }

    private fun setCurrentTab(tab: AppCompatButton) {
        if (currentTab != tab) {
            currentTab.setBackgroundColor(Color.WHITE)
            tab.setBackgroundColor(resources.getColor(R.color.tabColor))
            currentTab = tab
            edt_search.text.clear()

            setEmptyAdapter()
        }
    }

    private fun setEmptyAdapter() {
        recycler_users.adapter =
            RecyclerUsersAdapter(this, listOf(), viewModel, userIdList!!)
    }

    private fun initializeButtonSearch() {
        btn_search = binding.btnSearch
        btn_search.setOnClickListener {
            if (edt_search.text.isBlank())
                setEmptyAdapter()
            else {
                val queryString = edt_search.text.toString()
                if (currentTab == btn_tab_api) {
                    viewModel.setupUserList(queryString)
                    viewModel.userList.observe(this) {
                        if (currentTab == btn_tab_api)
                            setupRecycler(it)
                    }
                } else {
                    viewModel.setLocalTargetWord(queryString)
                    viewModel.localUserList.observe(this)
                    {
                        if (currentTab == btn_tab_local)
                            setupRecycler(it)
                    }
                }

            }
        }
    }

    private fun setupRecycler(source: List<UserItem>) {
        recycler_users.layoutManager?.let {
            recyclerViewState = recycler_users.layoutManager!!.onSaveInstanceState()
        }

        recyclerAdapter =
            RecyclerUsersAdapter(
                this,
                source.sortedBy { it.login },
                viewModel,
                userIdList!!
            )
        recycler_users.adapter = recyclerAdapter
        recycler_users.layoutManager = LinearLayoutManager(this)

        recyclerViewState?.let {
            recycler_users.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        }

        if (recycler_users.itemDecorationCount == 0)
            recycler_users.addItemDecoration(decorator!!)
    }

}