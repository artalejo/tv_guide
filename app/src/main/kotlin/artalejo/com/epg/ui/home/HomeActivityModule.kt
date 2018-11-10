package artalejo.com.epg.ui.home

import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    internal fun provideHomeView(homeActivity: HomeActivity): HomeView {
        return homeActivity
    }
}
