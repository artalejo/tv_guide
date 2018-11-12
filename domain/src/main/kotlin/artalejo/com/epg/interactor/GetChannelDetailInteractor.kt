package artalejo.com.epg.interactor

import artalejo.com.epg.Result
import artalejo.com.epg.model.ChannelDetailInfo
import artalejo.com.epg.repository.ChannelsRepository
import javax.inject.Inject

class GetChannelDetailInteractor @Inject constructor(private val repository: ChannelsRepository)
    : Interactor<ChannelDetailInfo, Unit>() {

    override fun run(params : Unit): Result<ChannelDetailInfo, *> {
        return repository.getChannelDetail()
    }
}