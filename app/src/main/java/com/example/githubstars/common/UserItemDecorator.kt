package com.example.githubstars.common

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class UserItemDecorator constructor(@ApplicationContext private val context: Context) :
    RecyclerView.ItemDecoration() {
    var mDivider: Drawable? = null

    init {
        mDivider = context.getDrawable(com.example.githubstars.R.drawable.line_divider)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = 100
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.getLayoutParams() as RecyclerView.LayoutParams
            val top: Int = child.getBottom() + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }
}