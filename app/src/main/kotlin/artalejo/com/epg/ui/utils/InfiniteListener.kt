package artalejo.com.epg.ui.utils

import artalejo.com.epg.ui.utils.adapter.ViewType

interface InfiniteListener {
    fun showInfoRetrieved()
    fun showEmptyView()
    fun hideEmptyView()
    fun scrollToPosition()
    fun changeInfiniteLoadingFinished(isInfiniteFinished: Boolean)
    fun addItemsToActivityItemList(newItems: List<ViewType>)
    var limitPerPage: Int
    var lastItemVisiblePosition: Int
}