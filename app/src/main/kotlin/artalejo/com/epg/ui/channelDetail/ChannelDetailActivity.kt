package artalejo.com.epg.ui.channelDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.Toolbar
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseActivity
import artalejo.com.epg.ui.entities.ChannelDetailViewEntity
import artalejo.com.epg.ui.utils.extensions.load
import artalejo.com.epg.ui.utils.extensions.setParallaxBehaviour
import artalejo.com.epg.ui.utils.extensions.setVisible
import kotlinx.android.synthetic.main.channel_detail_activity.*
import kotlinx.android.synthetic.main.channel_detail_header.view.*
import kotlinx.android.synthetic.main.coordinator_toolbar.*
import kotlinx.android.synthetic.main.coordinator_toolbar.view.*
import javax.inject.Inject

class ChannelDetailActivity : BaseActivity(), ChannelDetailView {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, ChannelDetailActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: ChannelDetailPresenter
    override var layout = R.layout.channel_detail_activity

    override fun onViewLoaded(savedInstanceState: Bundle?) {
        setUpToolbar()
    }

    override fun onResume() {
        super.onResume()
        presenter.getChannelDetails()
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransitionExit()
    }

    private fun setUpToolbar() {
        val toolbar = channel_detail_header.show_detail_toolbar as Toolbar
        toolbar.toolbar_back.setOnClickListener{ onBackPressed() }
    }

    private fun setUpToolbarParallaxBehaviour(title: String) {
        toolbar_back.setVisible()
        (channel_detail_header as AppBarLayout).setParallaxBehaviour(coordinator_toolbar_title, title)
    }

    override fun showChannelDetails(channelDetails: ChannelDetailViewEntity) {
        with (channelDetails) {
            setUpToolbarParallaxBehaviour(channelTitle)
            channel_detail_header.channel_image.load(images.logo, placeHolderResourceId = android.R.color.transparent)
            channel_logo.load(channelImages.logo)
            channel_title.text = channelTitle
            show_year.text = meta.year
            show_title.text = title
            show_genres.text = meta.genres.joinToString(getString(R.string.common_separator))
            show_cast.text = meta.cast.asSequence().map{ it.name }.joinToString(getString(R.string.comma_separator), prefix = getString(R.string.cast_header))
            show_creators.text = meta.creators.asSequence().map { it.name }.joinToString (getString(R.string.comma_separator), prefix = getString(R.string.creators_header))
            show_description.text = description

        }
    }

    override fun showLoading() = onLoading(listOf(channel_detail_header ,constraint_container), listOf(channel_detail_progress_bar))
    override fun hideLoading(){
        // Emulating the api call, the database is too quick and the loader is not shown as it should
        android.os.Handler().postDelayed({ onInfoRetrieved(listOf(channel_detail_header ,constraint_container), listOf(channel_detail_progress_bar)) }, 500)
    }

}