package artalejo.com.epg.dependencyinjection.fragment

import artalejo.com.epg.dependencyinjection.scope.PerFragment
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.emptyFragment.EmptyFragment
import artalejo.com.epg.ui.epg.EpgFragment
import artalejo.com.epg.ui.epg.EpgFragmentModule
import artalejo.com.epg.ui.epgDetail.EpgDetailFragment
import artalejo.com.epg.ui.epgDetail.EpgDetailFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjector {

    @PerFragment
    @ContributesAndroidInjector()
    abstract fun contributeBaseFragmentInjector(): BaseFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [(EpgFragmentModule::class)])
    abstract fun contributeEpgFragmentInjector(): EpgFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [(EpgDetailFragmentModule::class)])
    abstract fun contributeEpgDetailFragmentInjector(): EpgDetailFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [])
    abstract fun contributeEmptyFragmentInjector(): EmptyFragment

}