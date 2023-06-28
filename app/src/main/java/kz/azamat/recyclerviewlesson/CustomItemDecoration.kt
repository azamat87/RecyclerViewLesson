package kz.azamat.recyclerviewlesson

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class CustomItemDecoration: ItemDecoration() {

    private val rect = Rect()
    private val paint = Paint().apply {
        color = Color.YELLOW
        strokeWidth = 4f
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = 0
        val right = parent.width

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, rect)
            c.drawRect(left.toFloat(), rect.top.toFloat(), right.toFloat(), rect.top.toFloat(), paint)
        }
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.apply {
            left = 60
            top = 0
            left = 0
            bottom = 0
        }
    }


}