package artalejo.com.epg.dependencyinjection

import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.repository.channels.ChannelDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun providesChannelsDataRepository(channelDataRepository: ChannelDataRepository): ChannelsRepository {
        return channelDataRepository
    }

}
