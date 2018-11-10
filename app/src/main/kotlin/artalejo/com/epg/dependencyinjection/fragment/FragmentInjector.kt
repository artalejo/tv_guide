package artalejo.com.epg.dependencyinjection.fragment

import artalejo.com.epg.dependencyinjection.scope.PerFragment
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.epg.EpgFragment
import artalejo.com.epg.ui.epg.EpgFragmentModule
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

}