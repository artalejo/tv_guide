package artalejo.com.epg.repository.entities

//"channels": [
//{
//    "id": "sky1",
//    "title": "Sky 1",
//    "images": {
//    "logo": "https://img.noriginmedia.com/cloudberry/logo_sky1.png"
//},
//    "schedules": [
//    {
//        "title": "Cool Stuff",
//        "id": "dummy_program_id",
//        "start": "2018-10-26T00:00:00+02:00",
//        "end": "2018-10-26T00:40:00+02:00"
//    },
data class ChannelDataEntity(val id: String, val title: String, val images: ImagesDataEntity, val schedules: List<ScheduleDataEntity>)