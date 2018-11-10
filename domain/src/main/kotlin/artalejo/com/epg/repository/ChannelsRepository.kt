package artalejo.com.epg.repository

import artalejo.com.epg.Result
import artalejo.com.epg.model.ChannelInfo

interface ChannelsRepository {
    fun getChannels(): Result<ArrayList<ChannelInfo>, *>
    fun saveChannelsData(channelsData: String): Result<Boolean, *>
    fun setFavoriteStatus(channelId: String, isFavorited: Boolean): Result<Boolean, *>
}