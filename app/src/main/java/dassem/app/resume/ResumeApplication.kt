package dassem.app.resume

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dassem.app.resume.di.DaggerApplicationComponent


class ResumeApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .build().also { it.inject(this) }
    }
}