/*
* 사용자의 정보를 리스트의 형태로 표시하는 어댑터 클래스입니다.
* 생성자로부터 받은 ViewModel을 조작하여 내부 DB에 삽입 삭제를 실행합니다.
* */
package com.example.githubstars.Activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubstars.R
import com.example.githubstars.databinding.ItemListUserBinding
import com.example.githubstars.model.dto.UserItem

class RecyclerUsersAdapter constructor(
    private val context: Context,
    val userList: List<UserItem>,
    private val viewModel: MainViewModel,
    private val userIdList: List<Int>
) :
    RecyclerView.Adapter<RecyclerUsersAdapter.ViewHolder>() {

    private var currentHeader: Char = '.'

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemListUserBinding = ItemListUserBinding.inflate(inflater, parent, false)
        return ViewHolder(itemListUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(userList[position])

    override fun getItemCount() = userList.size

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

            binding.root.setOnClickListener {
                if (userIdList.contains(item.id))
                    viewModel.deleteUser(userList[adapterPosition])
                else
                    viewModel.insertUser(userList[adapterPosition])
            }

            if (userIdList.contains(item.id))
                binding.imgStar.setImageDrawable(context.resources.getDrawable(R.drawable.icon_star_colored))
            else
                binding.imgStar.setImageDrawable(context.resources.getDrawable(R.drawable.icon_star))
        }
    }
}