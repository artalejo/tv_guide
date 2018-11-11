package artalejo.com.epg.ui.splash

interface SplashView {
    fun getChannelsData(): String?
    fun getChannelsDetailData(): String?
    fun isDataSaved(isSaved: Boolean)
    fun dataSavedSuccessfully()
    fun dataNotSaved()
}