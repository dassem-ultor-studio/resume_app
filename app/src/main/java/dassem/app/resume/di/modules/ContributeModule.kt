package dassem.app.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dassem.app.resume.ui.ScrollingActivity

@Module
abstract class ContributeModule {

    @ContributesAndroidInjector
    abstract fun contributeScrollingActivity(): ScrollingActivity
}