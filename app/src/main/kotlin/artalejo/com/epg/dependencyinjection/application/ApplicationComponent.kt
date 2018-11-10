package artalejo.com.epg.dependencyinjection.application

import android.app.Application
import artalejo.com.epg.BaseApplication
import artalejo.com.epg.dependencyinjection.ActivityInjector
import artalejo.com.epg.dependencyinjection.DataModule
import artalejo.com.epg.dependencyinjection.fragment.FragmentInjector
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, AndroidInjectionModule::class, ActivityInjector::class, FragmentInjector::class, DataModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}