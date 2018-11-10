package artalejo.com.epg.ui.entities

import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.model.ImagesInfo
import artalejo.com.epg.model.ScheduleInfo
import java.text.SimpleDateFormat

val HOUR_FORMAT = SimpleDateFormat("HH:mm")

fun ChannelInfo.toChannelViewEntity() =
        ChannelViewEntity(this.id, this.title, this.images.toImagesViewEntity(), this.schedules.map { it.toScheduleViewEntity() })

fun ImagesInfo.toImagesViewEntity() = ImagesViewEntity(this.logo)

fun ScheduleInfo.toScheduleViewEntity() = ScheduleViewEntity(this.title, this.id, this.start, this.end, getScheduledTime(this) )

private fun getScheduledTime(scheduleViewEntity: ScheduleInfo) : String {
    val start = HOUR_FORMAT.format(scheduleViewEntity.start)
    val end = HOUR_FORMAT.format(scheduleViewEntity.end)
    return "$start - $end"
}