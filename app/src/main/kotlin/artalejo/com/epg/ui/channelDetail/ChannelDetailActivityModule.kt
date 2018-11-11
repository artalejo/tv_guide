package artalejo.com.epg.ui.channelDetail

import dagger.Module
import dagger.Provides

@Module
class ChannelDetailActivityModule {
    @Provides
    internal fun provideChannelDetailView(channelDetailActivity: ChannelDetailActivity): ChannelDetailView {
        return channelDetailActivity
    }
}