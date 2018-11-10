package artalejo.com.epg.ui.base

import android.view.View
import artalejo.com.epg.ui.utils.extensions.setGone
import artalejo.com.epg.ui.utils.extensions.setInvisible
import artalejo.com.epg.ui.utils.extensions.setVisible

interface LoadingBaseView {
    fun onLoading(infoView: View?, progressView : View?) {
        infoView?.setInvisible()
        progressView?.setVisible()
    }

    fun onLoading(infoViews: List<View?>, progressViews : List<View?>) {
        infoViews.forEach { it?.setInvisible() }
        progressViews.forEach { it?.setVisible() }
    }

    fun onInfoRetrieved(infoView: View?, progressView : View?) {
        infoView?.setVisible()
        progressView?.setGone()
    }

    fun onInfoRetrieved(infoViews: List<View?>, progressViews : List<View?>) {
        infoViews.forEach { it?.setVisible() }
        progressViews.forEach { it?.setInvisible() }
    }
}