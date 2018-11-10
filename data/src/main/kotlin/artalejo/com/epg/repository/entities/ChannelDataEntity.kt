package artalejo.com.epg.repository.entities

data class ChannelDataEntity(val id: String, val title: String, val isFavorite: Boolean,
                             val images: ImagesDataEntity, val schedules: List<ScheduleDataEntity>)