package artalejo.com.epg.ui.epgDetail

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.View
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.channelDetail.ChannelDetailPresenter
import artalejo.com.epg.ui.channelDetail.ChannelDetailView
import artalejo.com.epg.ui.entities.ChannelDetailViewEntity
import artalejo.com.epg.ui.utils.extensions.load
import artalejo.com.epg.ui.utils.extensions.setGone
import artalejo.com.epg.ui.utils.extensions.setParallaxBehaviour
import artalejo.com.epg.ui.utils.extensions.setVisible
import kotlinx.android.synthetic.main.channel_detail_fragment.*
import kotlinx.android.synthetic.main.channel_detail_header.*
import kotlinx.android.synthetic.main.channel_detail_header.view.*
import kotlinx.android.synthetic.main.coordinator_toolbar.*
import javax.inject.Inject

class EpgDetailFragment : BaseFragment(), ChannelDetailView {

    companion object {
        val TAG = EpgDetailFragment::class.java.simpleName
        val IS_LIVE = "IS_LIVE"
        fun newInstance(isLiveShow: Boolean = false) : EpgDetailFragment {
            val epgDetailFragment = EpgDetailFragment()
            val args = Bundle()
            args.putBoolean(IS_LIVE, isLiveShow)
            epgDetailFragment.arguments = args
            return epgDetailFragment
        }
    }

    @Inject
    lateinit var presenter: ChannelDetailPresenter
    override var layout = R.layout.channel_detail_fragment
    private var isLiveShow: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { isLiveShow = it.getBoolean(IS_LIVE, false) }
    }

    override fun onResume() {
        super.onResume()
        presenter.getChannelDetails()
    }

    private fun setUpToolbarParallaxBehaviour(title: String) {
        toolbar_back.setGone()
        (channel_detail_header as AppBarLayout).setParallaxBehaviour(coordinator_toolbar_title, title)
    }

    override fun showChannelDetails(channelDetails: ChannelDetailViewEntity) {
        if (isLiveShow) live_tag.setVisible() else live_tag.setGone()
        with (channelDetails) {
            setUpToolbarParallaxBehaviour(channelTitle)
            channel_detail_header.channel_image.load(images.logo, placeHolderResourceId = android.R.color.transparent)
            channel_logo.load(channelImages.logo)
            channel_title.text = channelTitle
            show_title.text = title
            show_year.text = meta.year
            show_genres.text = meta.genres.joinToString(getString(R.string.common_separator))
            show_cast.text = meta.cast.asSequence().map{ it.name }.joinToString(getString(R.string.comma_separator), prefix = getString(R.string.cast_header))
            show_creators.text = meta.creators.asSequence().map { it.name }.joinToString (getString(R.string.comma_separator), prefix = getString(R.string.creators_header))
            show_description.text = description
        }
    }

    override fun showLoading() = onLoading(listOf(channel_detail_header, constraint_container), listOf(channel_detail_progress_bar))
    override fun hideLoading() {
        // Emulating the api call, the database is too quick and the loader is not shown as it should
        android.os.Handler().postDelayed({
            onInfoRetrieved(listOf(channel_detail_header, constraint_container), listOf(channel_detail_progress_bar))
        }, 500)
    }
}