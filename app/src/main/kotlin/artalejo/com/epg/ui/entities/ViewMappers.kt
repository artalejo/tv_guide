package artalejo.com.epg.ui.entities

import artalejo.com.epg.model.*
import java.text.SimpleDateFormat

val HOUR_FORMAT = SimpleDateFormat("HH:mm")

fun ChannelInfo.toChannelViewEntity() =
        ChannelViewEntity(this.id, this.title, this.isFavorite,  this.images.toImagesViewEntity(), this.schedules.map { it.toScheduleViewEntity() })

fun ImagesInfo.toImagesViewEntity() = ImagesViewEntity(this.logo)

fun ScheduleInfo.toScheduleViewEntity() = ScheduleViewEntity(this.title, this.id, this.start, this.end, getScheduledTime(this) )

fun ChannelDetailInfo.toChannelDetailViewEntity() = ChannelDetailViewEntity (
        this.id, this.title, this.start, this.end, this.images.toImagesViewEntity(),
        this.channelTitle, this.channelImages.toImagesViewEntity(), this.description,
        this.meta.toChannelMetaViewEntity())

fun ChannelMetadataInfo.toChannelMetaViewEntity() = ChannelMetadataViewEntity(this.year,
        this.genres, this.cast.map { it.toChannelRoleViewEntity() }, this.creators.map { it.toChannelRoleViewEntity() })

fun ChannelRoleInfo.toChannelRoleViewEntity() = ChannelRoleViewEntity(this.name)

private fun getScheduledTime(scheduleViewEntity: ScheduleInfo) : String {
    val start = HOUR_FORMAT.format(scheduleViewEntity.start)
    val end = HOUR_FORMAT.format(scheduleViewEntity.end)
    return "$start - $end"
}