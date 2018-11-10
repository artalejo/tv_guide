package artalejo.com.epg.model

data class ChannelInfo(val id: String, val title: String, val images: ImagesInfo,
                       val schedules: List<ScheduleInfo>)