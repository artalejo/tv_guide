package artalejo.com.epg.ui.channelDetail

import artalejo.com.epg.ui.entities.ChannelDetailViewEntity

interface ChannelDetailView {
    fun showChannelDetails(channelDetails: ChannelDetailViewEntity)
    fun showLoading()
    fun hideLoading()
}