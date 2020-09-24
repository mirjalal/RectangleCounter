package aze.talmir.qwello.task.rectanglecounter

import android.app.Application
import aze.talmir.qwello.task.rectanglecounter.di.appComponents
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appComponents)
        }
    }
}
