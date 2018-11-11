package artalejo.com.epg.repository.entities

data class ChannelDetailDataEntity(
    val id: String,
    val title: String,
    val start: String,
    val end: String,
    val images: IconDataEntity,
    val channelTitle: String,
    val channelImages: ImagesDataEntity,
    val description: String,
    val meta: ChannelMetaDataEntity)