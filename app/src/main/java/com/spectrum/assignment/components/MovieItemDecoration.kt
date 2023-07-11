package com.spectrum.assignment.components

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieItemDecoration (private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.itemCount?.let { itemCount ->
            if (parent.getChildAdapterPosition(view) < itemCount - 1) {
                outRect.bottom = space
            }
        }
    }
}
