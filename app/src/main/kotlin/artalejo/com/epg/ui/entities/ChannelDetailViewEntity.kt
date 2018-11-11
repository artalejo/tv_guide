package artalejo.com.epg.ui.entities

data class ChannelDetailViewEntity(
        val id: String,
        val title: String,
        val start: String,
        val end: String,
        val images: ImagesViewEntity,
        val channelTitle: String,
        val channelImages: ImagesViewEntity,
        val description: String,
        val meta: ChannelMetadataViewEntity)