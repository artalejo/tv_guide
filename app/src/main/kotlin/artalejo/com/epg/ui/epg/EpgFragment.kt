package artalejo.com.epg.ui.epg

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import artalejo.com.epg.Navigator
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseActivity
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.entities.ChannelViewEntity
import artalejo.com.epg.ui.epg.adapter.ChannelsAdapterDelegate
import artalejo.com.epg.ui.utils.adapter.GenericAdapter
import kotlinx.android.synthetic.main.epg_fragment.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class EpgFragment : BaseFragment(), EpgView, ChannelsAdapterDelegate.ChannelClickListener {

    companion object {
        fun newInstance() = EpgFragment()
    }

    @Inject lateinit var channelsAdapter: GenericAdapter
    @Inject lateinit var presenter: EpgPresenter
    @Inject lateinit var navigator: Navigator
    override var layout = R.layout.epg_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(channels_recycler) {
            layoutManager = LinearLayoutManager(activity)
            channelsAdapter.setClickListener(this@EpgFragment)
            adapter = channelsAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getChannels()
    }

    override fun showChannels(channels: List<ChannelViewEntity>) {
        channelsAdapter.set(channels)
        hideLoading()
    }

    override fun favoriteStatusUpdated() {
        activity?.toast(getString(R.string.favorite_status_updated))
    }
    override fun showLoading() = onLoading(channels_recycler, channels_progress_bar)
    override fun hideLoading() = onInfoRetrieved(channels_recycler, channels_progress_bar)

    // Adapter listeners
    override fun onChannelClicked(channelEntity: ChannelViewEntity) {
        activity?.let { navigator.navigateToChannelDetail(it as BaseActivity) }
    }

    override fun onChannelFavoriteStatusChanged(channelEntityId: String, isFavorite: Boolean) {
        presenter.onChannelFavoriteStatusChanged(channelEntityId, isFavorite)
    }
}