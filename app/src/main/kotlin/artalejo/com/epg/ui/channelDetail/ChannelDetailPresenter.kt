package artalejo.com.epg.ui.channelDetail

import artalejo.com.epg.interactor.GetChannelDetailInteractor
import artalejo.com.epg.ui.entities.toChannelDetailViewEntity
import javax.inject.Inject

class ChannelDetailPresenter @Inject constructor(val view: ChannelDetailView,
                                                 private val getChannelDetailInteractor: GetChannelDetailInteractor) {

    fun getChannelDetails() {
        view.showLoading()
        getChannelDetailInteractor.execute(Unit) { result ->
            result.success { value ->
                view.showChannelDetails(value.toChannelDetailViewEntity())
                view.hideLoading()
            }
            result.failure {
                view.hideLoading()
            }
        }
    }
}