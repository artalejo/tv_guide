package artalejo.com.epg.interactor

import artalejo.com.epg.Result
import artalejo.com.epg.async.PostExecutionThread
import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.repository.ChannelsRepository
import javax.inject.Inject

class GetChannelsInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                                private val repository: ChannelsRepository)
    : Interactor<ArrayList<ChannelInfo>, String>(postExecutionThread) {

    override fun run(channelsData: String): Result<ArrayList<ChannelInfo>, *> {
        return repository.getChannels(channelsData)
    }
}