package artalejo.com.epg.ui.epg

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.entities.ChannelViewEntity
import artalejo.com.epg.ui.utils.adapter.GenericAdapter
import kotlinx.android.synthetic.main.epg_fragment.*
import java.io.IOException
import javax.inject.Inject

class EpgFragment : BaseFragment(), EpgView {

    companion object {
        val TAG: String = EpgFragment::class.java.simpleName
        fun newInstance() = EpgFragment()
    }

    @Inject lateinit var channelsAdapter: GenericAdapter
    @Inject lateinit var presenter: EpgPresenter
    override var layout = R.layout.epg_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun getChannelsData() = loadJSONFromAsset("epg.json")

    private fun loadJSONFromAsset(assetName: String): String? {
        activity?.let {
            var json: String?
            try {
                val inputStream = it.assets.open(assetName)
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
        } ?: return null
    }

    private fun setupRecyclerView() {

        with(channels_recycler) {
            layoutManager = LinearLayoutManager(activity)
//            channelsAdapter.setClickListener(this@EpgFragment)
            adapter = channelsAdapter
        }

//        val leagueDelegate = leaguesAdapter.getDelegateManager().getDelegateForViewTypeId(AdapterConstants.VIEW_TYPE_LEAGUE)
//        leagueDelegate?.let {
//            val playerInfo = (activity as HomeActivity).userDetails.playerInfo
//            if (playerInfo != null) (it as LeaguesAdapterDelegate).currentUserID = playerInfo.id.toString()
//        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getChannelsInteractor()
    }

    //
    override fun showChannels(channels: List<ChannelViewEntity>) {
        channelsAdapter.set(channels)
        hideLoading()
    }

    override fun showLoading() = onLoading(channels_recycler, channels_progress_bar)
    override fun hideLoading() = onInfoRetrieved(channels_recycler, channels_progress_bar)
}