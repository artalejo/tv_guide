package artalejo.com.epg

import android.content.Context
import artalejo.com.epg.ui.base.BaseActivity
import artalejo.com.epg.ui.channelDetail.ChannelDetailActivity
import artalejo.com.epg.ui.home.HomeActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigateToHome(context: Context) = context.startActivity(HomeActivity.getIntent(context))

    fun navigateToChannelDetail(activity: BaseActivity) {
        activity.startActivity(ChannelDetailActivity.getIntent(activity))
        activity.overridePendingTransitionEnter()
    }
}
