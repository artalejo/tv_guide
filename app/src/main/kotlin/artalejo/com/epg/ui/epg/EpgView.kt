package artalejo.com.epg.ui.epg

import artalejo.com.epg.ui.entities.ChannelViewEntity

interface EpgView {
    fun showChannels(channels: List<ChannelViewEntity>)
    fun favoriteStatusUpdated()
    fun showLoading()
    fun hideLoading()
}