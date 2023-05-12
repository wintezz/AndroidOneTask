package com.example.androidonetask.presentation.utils

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecorator(
    private val drawable: Drawable,
    private var horizontalPaddings: Int,
    private val viewTypes: List<Int>
) : RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            val type = parent.adapter?.getItemViewType(position)
            val nextType = parent.adapter?.getItemViewType(position + 1)
            if (viewTypes.contains(type) && viewTypes.contains(nextType)) {
                drawable.drawSeparator(child, parent, canvas)
            }
        }
    }

    private fun Drawable.drawSeparator(
        view: View, parent: RecyclerView, canvas: Canvas
    ) =
        apply {
            val dividerLeft = horizontalPaddings
            val dividerRight: Int = parent.width - horizontalPaddings
            val params = view.layoutParams as RecyclerView.LayoutParams

            val dividerTop: Int = view.bottom + params.bottomMargin
            val dividerBottom: Int = dividerTop + drawable.intrinsicHeight

            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            drawable.draw(canvas)
        }
}







