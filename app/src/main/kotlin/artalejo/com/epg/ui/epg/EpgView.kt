package artalejo.com.epg.ui.epg

import artalejo.com.epg.ui.entities.ChannelViewEntity

interface EpgView {
    fun showChannels(leagues: List<ChannelViewEntity>)
    fun showLoading()
    fun hideLoading()
}