package artalejo.com.epg.ui.epgDetail

import artalejo.com.epg.ui.channelDetail.ChannelDetailView
import dagger.Module
import dagger.Provides

@Module
class EpgDetailFragmentModule {
    @Provides
    internal fun provideEpgDetailView(epgDetailFragment: EpgDetailFragment): ChannelDetailView {
        return epgDetailFragment
    }
}
