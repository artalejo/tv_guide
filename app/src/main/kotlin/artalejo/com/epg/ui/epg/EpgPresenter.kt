package artalejo.com.epg.ui.epg

import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.ui.entities.toChannelViewEntity
import javax.inject.Inject

class EpgPresenter @Inject constructor(val view: EpgView,
                                       private val getChannelsInteractor: GetChannelsInteractor) {


    fun getChannelsInteractor() {
        view.showLoading()
        val allChannelData = view.getChannelsData()
        allChannelData?.let {
            getChannelsInteractor.execute(allChannelData) {
                result ->
                result.success { value ->
                    view.showChannels(value.map { it.toChannelViewEntity() })
                    view.hideLoading()
                }
                result.failure { exception ->
                    //                exceptionHandler.notifyException(view, exception)
                    view.hideLoading()
                }
            }
        } ?: view.hideLoading()
    }
}