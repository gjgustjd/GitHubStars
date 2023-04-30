/*
RecyclerUsersAdapter에 이미지를 로드하여 바인딩해주는 어댑터 클래스입니다.
* */
package com.example.githubstars.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object CommonBindingAdapter {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImageToCircleImageView(imageView: ImageView, url:String)
    {
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .thumbnail(0.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .into(imageView)
    }
}