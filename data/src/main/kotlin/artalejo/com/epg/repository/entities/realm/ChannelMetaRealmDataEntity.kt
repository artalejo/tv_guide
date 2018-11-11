package artalejo.com.epg.repository.entities.realm

import io.realm.RealmList
import io.realm.RealmObject

open class ChannelMetaRealmDataEntity() : RealmObject() {
    var year: String = ""
    var genres: RealmList<RealmString> = RealmList()
    var cast: RealmList<ChannelRoleRealmDataEntity> = RealmList()
    var creators: RealmList<ChannelRoleRealmDataEntity> = RealmList()

    constructor(year: String, genres: RealmList<RealmString>,
                cast: RealmList<ChannelRoleRealmDataEntity>,
                creators: RealmList<ChannelRoleRealmDataEntity>) : this() {
        this.year = year
        this.genres = genres
        this.cast = cast
        this.creators = creators
    }
}
