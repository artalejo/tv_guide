package artalejo.com.epg.ui.epg.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import artalejo.com.epg.R
import artalejo.com.epg.ui.entities.ChannelViewEntity
import artalejo.com.epg.ui.entities.ScheduleViewEntity
import artalejo.com.epg.ui.utils.adapter.*
import artalejo.com.epg.ui.utils.extensions.load
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelsAdapterDelegate : AdapterDelegate<List<ViewType>>() {

    override var viewTypeId = AdapterConstants.VIEW_TYPE_CHANNELS

    interface ChannelClickListener: BaseListener {
        fun onChannelClicked(channelEntity: ChannelViewEntity)
    }

    override fun isForViewType(items: List<ViewType>, position: Int) =
            items[position] is ChannelViewEntity

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(view, listener, context)
    }

    override fun onBindViewHolder(items: List<ViewType>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {
        (holder as? ChannelViewHolder)?.bind(items[position] as ChannelViewEntity)
    }

    class ChannelViewHolder(view: View, val listener: BaseListener?,
                           val context: Context) : ItemViewHolder(view) {

        fun bind(channelEntity: ChannelViewEntity) {
            with(channelEntity) {
                itemView.channel_logo.load(images.logo, placeHolderResourceId = R.color.colorPrimaryDark)
                if (schedules.isNotEmpty()) {
                    val scheduleViewEntity = schedules[0]
                    itemView.schedule_time.text = scheduleViewEntity.scheduleTime
                    itemView.show_title.text = scheduleViewEntity.title
                    itemView.show_progress.progress = 50 // todo find out progress
                }
                itemView.setOnClickListener {
                    listener?.let { (it as ChannelClickListener).onChannelClicked(channelEntity) }
                }
            }
        }
    }
}