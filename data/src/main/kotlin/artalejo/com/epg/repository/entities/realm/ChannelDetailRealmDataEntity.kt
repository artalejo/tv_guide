package artalejo.com.epg.repository.entities.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChannelDetailRealmDataEntity() : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var title: String = ""
    var start: String = ""
    var end: String = ""
    var images: ImagesRealmDataEntity = ImagesRealmDataEntity()
    var channelTitle: String = ""
    var channelImages: ImagesRealmDataEntity = ImagesRealmDataEntity()
    var description: String = ""
    var meta: ChannelMetaRealmDataEntity = ChannelMetaRealmDataEntity()

    constructor(id: String, title: String, start: String, end: String,
                images: ImagesRealmDataEntity, channelTitle: String,
                channelImages: ImagesRealmDataEntity, description: String,
                meta: ChannelMetaRealmDataEntity) : this() {
        this.id = id
        this.title = title
        this.start = start
        this.end = end
        this.images = images
        this.channelTitle = channelTitle
        this.channelImages = channelImages
        this.description = description
        this.meta = meta
    }
}
