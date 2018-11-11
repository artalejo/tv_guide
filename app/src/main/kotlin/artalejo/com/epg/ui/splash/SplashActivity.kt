package artalejo.com.epg.ui.splash

import artalejo.com.epg.Navigator
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseActivity
import org.jetbrains.anko.toast
import java.io.IOException
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    private val FILENAME_CHANNELS = "epg.json"
    private val FILENAME_DETAILS = "epg_details.json"

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var presenter: SplashPresenter

    override var layout = R.layout.activity_splash

    override fun onViewLoaded() {}

    override fun onResume() {
        super.onResume()
        presenter.isChannelDataAlreadySaved()
    }

    override fun isDataSaved(isSaved: Boolean) {
        if (isSaved) dataSavedSuccessfully()
        else {
            presenter.saveChannelsData()
        }
    }

    override fun getChannelsData() = loadJSONFromAsset(FILENAME_CHANNELS)
    override fun getChannelsDetailData() = loadJSONFromAsset(FILENAME_DETAILS)

    private fun loadJSONFromAsset(assetName: String): String? {
        var json: String?
        try {
            val inputStream = this.assets.open(assetName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    override fun dataSavedSuccessfully() {
        navigator.navigateToHome(this)
    }

    override fun dataNotSaved() {
        toast(getString(R.string.load_channels_error))
    }
}