package artalejo.com.epg.dependencyinjection

import artalejo.com.epg.dependencyinjection.scope.PerActivity
import artalejo.com.epg.ui.home.HomeActivity
import artalejo.com.epg.ui.home.HomeActivityModule
import artalejo.com.epg.ui.splash.SplashActivity
import artalejo.com.epg.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjector {

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun contributeSplashInjector(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun contributeHomeInjector(): HomeActivity

}