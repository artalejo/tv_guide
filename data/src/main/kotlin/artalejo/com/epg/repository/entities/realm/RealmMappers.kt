package artalejo.com.epg.repository.entities.realm

import artalejo.com.epg.repository.entities.ChannelDataEntity
import artalejo.com.epg.repository.entities.ImagesDataEntity
import artalejo.com.epg.repository.entities.ScheduleDataEntity
import io.realm.RealmList

fun ChannelDataEntity.toChannelRealmDataEntity() = ChannelRealmDataEntity(this.id, this.title, this.isFavorite, this.images.toImagesRealmDataEntity(), getScheduleRealmDataList(this.schedules))

private fun getScheduleRealmDataList(schedules: List<ScheduleDataEntity>) : RealmList<ScheduleRealmDataEntity> {
    val realmList = RealmList<ScheduleRealmDataEntity>()
    for (schedule: ScheduleDataEntity in schedules) {
        realmList.add(schedule.toScheduleRealmDataEntity())
    }
    return realmList
}

fun ImagesDataEntity.toImagesRealmDataEntity() = ImagesRealmDataEntity(this.logo)

fun ScheduleDataEntity.toScheduleRealmDataEntity() = ScheduleRealmDataEntity(this.title, this.id,
                                                                             this.start, this.end)

fun ChannelRealmDataEntity.toChannelDataEntity() = ChannelDataEntity(this.id, this.title, this.isFavorite, this.images.toImagesDataEntity(), this.schedules.map { it.toScheduleDataEntity() })

fun ImagesRealmDataEntity.toImagesDataEntity() = ImagesDataEntity(this.logo)

fun ScheduleRealmDataEntity.toScheduleDataEntity() = ScheduleDataEntity(this.title, this.id, this.start, this.end)