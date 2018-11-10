package artalejo.com.epg.ui.splash

import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.interactor.SaveChannelsDataInteractor
import javax.inject.Inject

class SplashPresenter @Inject constructor(val view: SplashView,
                                          private val getChannelsInteractor: GetChannelsInteractor,
                                          private val saveChannelsDataInteractor: SaveChannelsDataInteractor) {


    fun isChannelDataAlreadySaved() {
        getChannelsInteractor.execute(Unit) {
                result ->
                result.success {
                    if (it.isEmpty()) view.isDataSaved(false)
                    else view.isDataSaved(true)

                }
                result.failure { view.isDataSaved(false) }
            }
    }

    fun saveChannelsData(channelsData: String?) {
        channelsData?.let {
            saveChannelsDataInteractor.execute(channelsData) {
                result ->
                result.success { view.dataSavedSuccessfully() }
                result.failure { view.dataNotSaved() }
            }
        } ?: view.dataNotSaved()
    }
}