package artalejo.com.epg.repository.entities.realm

import artalejo.com.epg.repository.entities.*
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

fun IconDataEntity.toImagesRealmDataEntity() = ImagesRealmDataEntity(this.icon)

fun ScheduleDataEntity.toScheduleRealmDataEntity() = ScheduleRealmDataEntity(this.title, this.id,
                                                                             this.start, this.end)

fun ChannelDetailDataEntity.toChannelDetailRealmDataEntity() = ChannelDetailRealmDataEntity(
        this.id, this.title, this.start, this.end, this.images.toImagesRealmDataEntity(),
        this.channelTitle, this.channelImages.toImagesRealmDataEntity(), this.description,
        this.meta.toChannelMetaRealmDataEntity())

fun ChannelMetaDataEntity.toChannelMetaRealmDataEntity() = ChannelMetaRealmDataEntity(this.year,
        getGenresRealmDataList(this.genres), getRolesRealmDataList(cast), getRolesRealmDataList(this.creators))

fun ChannelRoleDataEntity.toChannelRoleRealmDataEntity() = ChannelRoleRealmDataEntity(this.name)

private fun getRolesRealmDataList(roles: List<ChannelRoleDataEntity>) :
        RealmList<ChannelRoleRealmDataEntity> {
    val realmList = RealmList<ChannelRoleRealmDataEntity>()
    for (role: ChannelRoleDataEntity in roles) {
        realmList.add(role.toChannelRoleRealmDataEntity())
    }
    return realmList
}

private fun getGenresRealmDataList(genres: List<String>) :
        RealmList<RealmString> {
    val realmList = RealmList<RealmString>()
    for (genre: String in genres) { realmList.add(RealmString(genre)) }
    return realmList
}

fun ChannelRealmDataEntity.toChannelDataEntity() = ChannelDataEntity(this.id, this.title, this.isFavorite, this.images.toImagesDataEntity(), this.schedules.map { it.toScheduleDataEntity() })

fun ImagesRealmDataEntity.toImagesDataEntity() = ImagesDataEntity(this.logo)

fun ImagesRealmDataEntity.toIconDataEntity() = IconDataEntity(this.logo)


fun ScheduleRealmDataEntity.toScheduleDataEntity() = ScheduleDataEntity(this.title, this.id, this.start, this.end)

fun ChannelDetailRealmDataEntity.toChannelDetailDataEntity() = ChannelDetailDataEntity(this.id,
        this.title, this.start, this.end, this.images.toIconDataEntity(), this.channelTitle,
        this.channelImages.toImagesDataEntity(), this.description, this.meta.toChannelMetaDataEntity())

fun ChannelMetaRealmDataEntity.toChannelMetaDataEntity() = ChannelMetaDataEntity(this.year,
        this.genres.map { it.genre }, this.cast.map { it.toChannelRoleDataEntity()}, this.creators.map { it
        .toChannelRoleDataEntity()})

fun ChannelRoleRealmDataEntity.toChannelRoleDataEntity() = ChannelRoleDataEntity(this.name)