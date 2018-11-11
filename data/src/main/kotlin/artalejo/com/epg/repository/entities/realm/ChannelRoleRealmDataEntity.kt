package artalejo.com.epg.repository.entities.realm

import io.realm.RealmObject

open class ChannelRoleRealmDataEntity(): RealmObject() {
    var name: String = ""

    constructor(name: String): this() {
        this.name = name
    }
}