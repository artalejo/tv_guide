package artalejo.com.epg.model

data class ChannelMetadataInfo(
        val year: String,
        val genres: List<String>,
        val cast: List<ChannelRoleInfo>,
        val creators: List<ChannelRoleInfo>)