package artalejo.com.epg.ui.epg

import artalejo.com.epg.ui.entities.ChannelViewEntity

interface EpgView {
    fun getChannelsData(): String?
    fun showChannels(leagues: List<ChannelViewEntity>)
    fun showLoading()
    fun hideLoading()
}