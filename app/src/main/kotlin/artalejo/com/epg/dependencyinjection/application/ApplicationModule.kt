package artalejo.com.epg.dependencyinjection.application

import android.app.Application
import android.content.Context
import artalejo.com.epg.UiThread
import artalejo.com.epg.async.PostExecutionThread
import artalejo.com.epg.dependencyinjection.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesPostExecutionThread(): PostExecutionThread = UiThread()

}