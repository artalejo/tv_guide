package artalejo.com.epg.interactor

import artalejo.com.epg.Result
import artalejo.com.epg.async.PostExecutionThread
import artalejo.com.epg.repository.ChannelsRepository
import javax.inject.Inject

class SaveChannelDetailDataInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                                          private val repository: ChannelsRepository)
    : Interactor<Boolean, String>(postExecutionThread) {

    override fun run(channelDetailData: String): Result<Boolean, *> {
        return repository.saveChannelDetailsData(channelDetailData)
    }
}