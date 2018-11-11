package artalejo.com.epg.repository.entities

import artalejo.com.epg.model.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

fun ChannelDataEntity.toChannelInfo() =
        ChannelInfo(this.id, this.title, this.isFavorite, this.images.toImagesInfo(), this.schedules.map{ it.toScheduleInfo() })

fun ImagesDataEntity.toImagesInfo() = ImagesInfo(this.logo)

fun IconDataEntity.toImagesInfo() = ImagesInfo(this.icon)

fun ScheduleDataEntity.toScheduleInfo() = ScheduleInfo(this.title, this.id, formatDate(this.start),
        formatDate(this.end))

fun ChannelDetailDataEntity.toChannelDetailInfo() = ChannelDetailInfo (
        this.id, this.title, this.start, this.end, this.images.toImagesInfo(),
        this.channelTitle, this.channelImages.toImagesInfo(), this.description,
        this.meta.toChannelMetaInfo())

fun ChannelMetaDataEntity.toChannelMetaInfo() = ChannelMetadataInfo(this.year,
        this.genres, this.cast.map { it.toChannelRoleInfo() }, this.creators.map { it.toChannelRoleInfo() })

fun ChannelRoleDataEntity.toChannelRoleInfo() = ChannelRoleInfo(this.name)

private fun formatDate(date: String): Date {
    return try {
        DATE_FORMAT.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        Date()
    }
}
