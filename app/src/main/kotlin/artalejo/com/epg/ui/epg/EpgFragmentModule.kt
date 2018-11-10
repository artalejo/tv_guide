package artalejo.com.epg.ui.epg

import artalejo.com.epg.dependencyinjection.scope.PerFragment
import artalejo.com.epg.ui.epg.adapter.ChannelsAdapterDelegate
import artalejo.com.epg.ui.utils.adapter.AdapterDelegate
import artalejo.com.epg.ui.utils.adapter.ViewType
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

@Module
class EpgFragmentModule {

    @Provides
    internal fun provideEpgView(epgFragment: EpgFragment): EpgView {
        return epgFragment
    }

    @Provides
    @PerFragment
    @ElementsIntoSet
    internal fun provideChannelsAdapterDelegates() : MutableSet<AdapterDelegate<List<ViewType>>> {
        val delegates = LinkedHashSet<AdapterDelegate<List<ViewType>>>()
        delegates.add(ChannelsAdapterDelegate())
        return delegates
    }
}