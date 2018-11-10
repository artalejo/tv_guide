package artalejo.com.epg.repository.entities.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChannelRealmDataEntity() : RealmObject() {
    @PrimaryKey var id: String = ""
    var title: String = ""
    var images: ImagesRealmDataEntity = ImagesRealmDataEntity()
    var isFavorite: Boolean = false
    var schedules: RealmList<ScheduleRealmDataEntity> = RealmList()

    constructor(id: String, title: String, favorited: Boolean, images: ImagesRealmDataEntity,
                schedules: RealmList<ScheduleRealmDataEntity>) : this() {
        this.id = id
        this.title = title
        this.images = images
        this.isFavorite = favorited
        this.schedules = schedules
    }
}