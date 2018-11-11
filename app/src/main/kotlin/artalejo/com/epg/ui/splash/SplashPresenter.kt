package artalejo.com.epg.ui.splash

import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.interactor.SaveChannelDetailDataInteractor
import artalejo.com.epg.interactor.SaveChannelsDataInteractor
import javax.inject.Inject

class SplashPresenter @Inject constructor(val view: SplashView,
                                          private val getChannelsInteractor: GetChannelsInteractor,
                                          private val saveChannelsDataInteractor: SaveChannelsDataInteractor,
                                          private val saveChannelDetailDataInteractor: SaveChannelDetailDataInteractor) {


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

    fun saveChannelsData() {
        view.getChannelsData()?.let {
            saveChannelsDataInteractor.execute(it) {
                result ->
                result.success {
                    view.getChannelsDetailData()?.let {
                        saveChannelDetailDataInteractor.execute(it) {
                            result ->
                            result.success { view.dataSavedSuccessfully() }
                            result.failure { view.dataNotSaved() }
                        }
                    }
                }
                result.failure { view.dataNotSaved() }
            }
        } ?: view.dataNotSaved()
    }
}