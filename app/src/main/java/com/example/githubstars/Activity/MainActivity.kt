package com.example.githubstars.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.os.WorkSource
import android.widget.EditText
import androidx.annotation.RequiresApi
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
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var userList: List<UserItem>? = null
    private var userIdList: List<Int>? = null
    private var localUserList: List<UserItem>? = null
    private lateinit var recycler_users: RecyclerView
    private lateinit var edt_search: EditText
    private lateinit var btn_search: AppCompatButton
    private lateinit var btn_tab_api: AppCompatButton
    private lateinit var btn_tab_local: AppCompatButton
    private lateinit var recyclerAdapter: RecyclerAPIUsersAdapter
    private var recyclerViewState: Parcelable? = null
    private lateinit var currentTab:AppCompatButton


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
            setupRecycler()
        }
    }

    private fun initializeView() {
        recycler_users = binding.recyclerItems
        edt_search = binding.edtSearch
        btn_tab_api = binding.btnTabApi
        currentTab=btn_tab_api
        btn_tab_api.setOnClickListener {
            if (!currentTab.equals(btn_tab_api)) {
                currentTab = btn_tab_api
                btn_tab_local.setBackgroundColor(Color.WHITE)
                btn_tab_api.setBackgroundColor(resources.getColor(R.color.tabColor))
                edt_search.text.clear()
                recycler_users.adapter =
                    RecyclerAPIUsersAdapter(this, listOf(), viewModel, userIdList!!)
            }
        }
        btn_tab_local = binding.btnTabLocal
        btn_tab_local.setOnClickListener {
            if (!currentTab.equals(btn_tab_local)) {
                currentTab = btn_tab_local
                btn_tab_api.setBackgroundColor(Color.WHITE)
                btn_tab_local.setBackgroundColor(resources.getColor(R.color.tabColor))
                edt_search.text.clear()
                recycler_users.adapter =
                    RecyclerAPIUsersAdapter(this, listOf(), viewModel, userIdList!!)
            }
        }
        initializeButtonSearch()
    }

    private fun initializeButtonSearch() {
        btn_search = binding.btnSearch
        btn_search.setOnClickListener {
            if (edt_search.text.isBlank())
                recycler_users.adapter =
                    RecyclerAPIUsersAdapter(this, listOf(), viewModel, userIdList!!)
            else {
                if (currentTab.equals(btn_tab_api)) {
                    viewModel.setupUserList(edt_search.text.toString())
                    viewModel.userList.observe(this) {
                        userList = it
                        setupRecycler()
                    }
                } else {
                    viewModel.setLocalTargetWord(edt_search.text.toString())
                    viewModel.localUserList.observe(this)
                    {
                        localUserList = it
                        setupRecycler()
                    }
                }
            }
        }
    }

    private fun setupRecycler() {
        lateinit var source: List<UserItem>
        if (currentTab.equals(btn_tab_api)) {
            if (userList == null || userIdList == null)
                return
            else
                source = userList!!
        } else {
            if (localUserList == null)
                return
            else
                source = localUserList!!
        }

        if (recycler_users.layoutManager != null)
            recyclerViewState = recycler_users.layoutManager?.onSaveInstanceState()!!

        recyclerAdapter =
            RecyclerAPIUsersAdapter(
                this,
                source!!.sortedBy { it.login },
                viewModel,
                userIdList!!
            )
        recycler_users.adapter = recyclerAdapter
        recycler_users.layoutManager = LinearLayoutManager(this)

        if (recyclerViewState != null)
            recycler_users.layoutManager!!.onRestoreInstanceState(recyclerViewState)

        if (recycler_users.itemDecorationCount == 0)
            recycler_users.addItemDecoration(UserItemDecorator(this))
    }

}