package com.example.githubstars.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

object CommonBindingAdapter{

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