package com.example.githubstars.Activity

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubstars.databinding.ItemListUserBinding
import javax.inject.Inject
import com.example.githubstars.model.dto.UserItem

class RecyclerAPIUsersAdapter @Inject constructor(private val userList: List<UserItem>) :
    RecyclerView.Adapter<RecyclerAPIUsersAdapter.ViewHolder>() {

    private var currentHeader: Char = '.'

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var itemListUserBinding = ItemListUserBinding.inflate(inflater, parent, false)
        return ViewHolder(itemListUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(val binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserItem) {
            binding.userData = item

            if (!currentHeader.equals(item.login[0])) {
                currentHeader = item.login[0]
                binding.txtHeader.text = currentHeader.toString()
                binding.txtHeader.visibility = View.VISIBLE
            } else {
                binding.txtHeader.visibility = View.GONE
            }
        }
    }
}