package artalejo.com.epg

import android.content.Context
import artalejo.com.epg.ui.home.HomeActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigateToHome(context: Context) = context.startActivity(HomeActivity.getIntent(context))

}
