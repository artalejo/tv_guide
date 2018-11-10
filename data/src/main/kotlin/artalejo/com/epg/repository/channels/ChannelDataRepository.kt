package artalejo.com.epg.repository.channels

import artalejo.com.epg.Result
import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.repository.entities.AllChannelsDataEntity
import artalejo.com.epg.repository.entities.ChannelDataEntity
import artalejo.com.epg.repository.entities.realm.ChannelRealmDataEntity
import artalejo.com.epg.repository.entities.realm.toChannelDataEntity
import artalejo.com.epg.repository.entities.realm.toChannelRealmDataEntity
import artalejo.com.epg.repository.entities.toChannelInfo
import com.google.gson.Gson
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.save
import io.realm.Realm
import javax.inject.Inject



class ChannelDataRepository @Inject constructor(): ChannelsRepository {

    override fun getChannels(): Result<ArrayList<ChannelInfo>, *> {
        return Result.Success( ArrayList(ChannelRealmDataEntity().queryAll().map { it.toChannelDataEntity().toChannelInfo() }))
    }

    override fun saveChannelsData(channelsData: String): Result<Boolean, *> {
        val allChannelsEntity = Gson().fromJson(channelsData, AllChannelsDataEntity::class.java)
        for (channelData: ChannelDataEntity in allChannelsEntity.channels) { channelData.toChannelRealmDataEntity().save() }
        return Result.Success (true)
    }

    override fun setFavoriteStatus(channelId: String, isFavorite: Boolean): Result<Boolean, *> {
        val realm = Realm.getDefaultInstance()
        val channelRealmDataEntity: ChannelRealmDataEntity? = realm.where(ChannelRealmDataEntity::class.java).equalTo("id", channelId).findFirst()

        channelRealmDataEntity?.let {
            realm.beginTransaction()
            it.isFavorite = isFavorite
            realm.commitTransaction()
            return Result.Success (true)
        } ?: return Result.Failure ()
    }
}