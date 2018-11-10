package artalejo.com.epg.ui.splash

import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {
    @Provides
    internal fun provideSplashActivityView(splashActivity: SplashActivity): SplashView {
        return splashActivity
    }
}