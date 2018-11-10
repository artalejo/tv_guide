package artalejo.com.epg.ui.entities

import artalejo.com.epg.ui.utils.adapter.AdapterConstants
import artalejo.com.epg.ui.utils.adapter.ViewType

data class ChannelViewEntity(val id: String, val title: String, var isFavorite: Boolean,
                             val images: ImagesViewEntity,
                             val schedules: List<ScheduleViewEntity>) : ViewType {
    override fun getViewType() = AdapterConstants.VIEW_TYPE_CHANNELS
}