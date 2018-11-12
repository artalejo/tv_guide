package artalejo.com.epg.dependencyinjection.application

import android.app.Application
import android.content.Context
import artalejo.com.epg.dependencyinjection.qualifier.ApplicationContext
import artalejo.com.epg.ui.base.Android
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

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
    internal fun providesContinuation(): AbstractCoroutineContextElement = Android


}