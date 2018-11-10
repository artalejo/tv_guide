package artalejo.com.epg.repository.entities

import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.model.ImagesInfo
import artalejo.com.epg.model.ScheduleInfo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

fun ChannelDataEntity.toChannelInfo() =
        ChannelInfo(this.id, this.title, this.isFavorite, this.images.toImagesInfo(), this.schedules.map{ it.toScheduleInfo() })

fun ImagesDataEntity.toImagesInfo() = ImagesInfo(this.logo)

fun ScheduleDataEntity.toScheduleInfo() = ScheduleInfo(this.title, this.id, formatDate(this.start),
        formatDate(this.end))

private fun formatDate(date: String): Date {
    return try {
        DATE_FORMAT.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        Date()
    }
}
