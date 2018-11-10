package artalejo.com.epg.repository.entities.realm

import io.realm.RealmObject

open class ScheduleRealmDataEntity() : RealmObject() {
    var title: String = ""
    var id: String = ""
    var start: String = ""
    var end: String = ""

    constructor(title: String, id: String, start: String, end: String) : this() {
        this.title = title
        this.id = id
        this.start = start
        this.end = end
    }

}