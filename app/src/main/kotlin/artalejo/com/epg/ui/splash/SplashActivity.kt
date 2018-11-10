package artalejo.com.epg.ui.splash;

import android.os.Handler
import artalejo.com.epg.Navigator
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity: BaseActivity() {
    private val SPLASH_DELAY_MS: Long = 500
    private val handler: Handler = Handler()

    @Inject lateinit var navigator: Navigator
    override var layout = R.layout.activity_splash
    override fun onViewLoaded() {}

    override fun onResume() {
        super.onResume()
        handler.postDelayed({navigator.navigateToHome(this)}, SPLASH_DELAY_MS)
    }
}