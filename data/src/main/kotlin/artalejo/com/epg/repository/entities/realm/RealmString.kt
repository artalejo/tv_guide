package artalejo.com.epg.repository.entities.realm

import io.realm.RealmObject

open class RealmString() : RealmObject() {
    var genre: String = ""

    constructor(genre: String) : this() {
        this.genre = genre
    }
}