package dev.dontsu.coinmonitoring

import android.app.Application
import android.content.Context
import timber.log.Timber

class CoinMonitoringApplication: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(CoinMonitoringDebugTree())
        }
    }

    private class CoinMonitoringDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String =
        "${element.fileName}:${element.lineNumber}:${element.methodName}"
    }

    companion object {
        private var instance: CoinMonitoringApplication? = null
        fun context() : Context = instance!!.applicationContext

    }

}
