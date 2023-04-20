package co.develhope.meteoapp.di

import android.app.Application
import co.develhope.meteoapp.di.startKoin

class MeteoAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }
}
