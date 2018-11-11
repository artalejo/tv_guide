package artalejo.com.epg.ui.entities

data class ChannelMetadataViewEntity(
        val year: String,
        val genres: List<String>,
        val cast: List<ChannelRoleViewEntity>,
        val creators: List<ChannelRoleViewEntity>)