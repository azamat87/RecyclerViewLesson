package kz.azamat.recyclerviewlesson

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var onLoadMore: (() -> Unit) ?= null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val lastVisibleElement = layoutManager.findLastVisibleItemPosition()

        if (lastVisibleElement + 1 == totalItemCount) {
            onLoadMore?.invoke()
        }
    }

    fun loadMore(callback: ()-> Unit) {
        onLoadMore = callback
    }

}