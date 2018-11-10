package artalejo.com.epg.ui.base

import android.view.View
import artalejo.com.epg.ui.utils.extensions.setGone
import artalejo.com.epg.ui.utils.extensions.setVisible

interface EmptyBaseView {

    fun showEmptyView(emptyView: View?, infoView: View?) {
        emptyView?.setVisible()
        infoView?.setGone()
    }

    fun hideEmptyView(emptyView: View?, infoView : View?) {
        emptyView?.setGone()
        infoView?.setVisible()
    }
}