package artalejo.com.epg.interactor

import artalejo.com.epg.Result
import artalejo.com.epg.repository.ChannelsRepository
import javax.inject.Inject

class FavoriteChannelInteractor @Inject constructor(private val repository: ChannelsRepository)
    : Interactor<Boolean, String>() {

    override fun run(channelId: String): Result<Boolean, *> {
        return repository.setFavoriteStatus(channelId, true)
    }
}