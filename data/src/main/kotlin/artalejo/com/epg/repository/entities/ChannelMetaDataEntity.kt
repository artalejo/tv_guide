package artalejo.com.epg.repository.entities

data class ChannelMetaDataEntity(
    val year: String,
    val genres: List<String>,
    val cast: List<ChannelRoleDataEntity>,
    val creators: List<ChannelRoleDataEntity>)