package dassem.app.resume.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dassem.app.resume.ResumeApplication
import dassem.app.resume.di.modules.ContributeModule
import dassem.app.resume.network.NetworkModule
import dassem.app.resume.viewmodel.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
        AndroidSupportInjectionModule::class,
        ContributeModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ResumeApplication> {

    override fun inject(application: ResumeApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}