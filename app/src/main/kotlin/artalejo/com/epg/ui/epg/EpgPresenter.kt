package artalejo.com.epg.ui.epg

import artalejo.com.epg.interactor.FavoriteChannelInteractor
import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.interactor.UnfavoriteChannelInteractor
import artalejo.com.epg.ui.entities.toChannelViewEntity
import javax.inject.Inject

class EpgPresenter @Inject constructor(val view: EpgView,
                                       private val getChannelsInteractor: GetChannelsInteractor,
                                       private val favoriteChannelInteractor: FavoriteChannelInteractor,
                                       private val unfavoriteChannelInteractor: UnfavoriteChannelInteractor) {

    fun onChannelFavoriteStatusChanged(channelId: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteChannelInteractor.execute(channelId) { }
        } else {
            unfavoriteChannelInteractor.execute(channelId) { }
        }
    }

    fun getChannels() {
        view.showLoading()
        getChannelsInteractor.execute(Unit) { result ->
            result.success { value ->
                view.showChannels(value.map { it.toChannelViewEntity() })
                view.hideLoading()
            }
            result.failure {
                view.hideLoading()
            }
        }
    }
}