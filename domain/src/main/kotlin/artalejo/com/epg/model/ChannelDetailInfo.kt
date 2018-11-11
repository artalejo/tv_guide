package artalejo.com.epg.model

data class ChannelDetailInfo(
        val id: String,
        val title: String,
        val start: String,
        val end: String,
        val images: ImagesInfo,
        val channelTitle: String,
        val channelImages: ImagesInfo,
        val description: String,
        val meta: ChannelMetadataInfo)