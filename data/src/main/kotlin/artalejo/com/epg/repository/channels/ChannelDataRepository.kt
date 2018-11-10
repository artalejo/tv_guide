package artalejo.com.epg.repository.channels

import artalejo.com.epg.Result
import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.repository.entities.AllChannelsDataEntity
import artalejo.com.epg.repository.entities.toChannelInfo
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ChannelDataRepository @Inject constructor(): ChannelsRepository {

    override fun getChannels(channelsData: String): Result<ArrayList<ChannelInfo>, *> {
        val allChannelsEntity = Gson().fromJson(channelsData, AllChannelsDataEntity::class.java)
        return Result.Success( ArrayList(allChannelsEntity.channels.map { it.toChannelInfo() }))
    }

    private fun getAllChannelsJson(): String? {
        return try {
            val inputStream = File("epg.json").inputStream()
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}