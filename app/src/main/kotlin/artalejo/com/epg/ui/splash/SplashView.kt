package artalejo.com.epg.ui.splash

interface SplashView {
    fun isDataSaved(isSaved: Boolean)
    fun dataSavedSuccessfully()
    fun dataNotSaved()
}