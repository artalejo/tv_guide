package artalejo.com.epg.ui.entities

import java.util.*

data class ScheduleViewEntity(val title: String, val id: String, val start: Date, val end: Date,
                              val scheduleTime: String)