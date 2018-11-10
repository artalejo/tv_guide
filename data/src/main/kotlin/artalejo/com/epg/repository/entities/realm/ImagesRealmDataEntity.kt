package artalejo.com.epg.repository.entities.realm

import io.realm.RealmObject

open class ImagesRealmDataEntity(): RealmObject() {
    var logo: String = ""

    constructor(logo: String): this() {
        this.logo = logo
    }
}