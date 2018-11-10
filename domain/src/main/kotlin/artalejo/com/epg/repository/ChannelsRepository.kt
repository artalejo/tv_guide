package artalejo.com.epg.repository

import artalejo.com.epg.Result
import artalejo.com.epg.model.ChannelInfo

interface ChannelsRepository {
    fun getChannels(channelsData: String): Result<ArrayList<ChannelInfo>, *>
}