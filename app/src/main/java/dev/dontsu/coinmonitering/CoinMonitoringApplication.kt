package dev.dontsu.coinmonitering

import android.app.Application
import timber.log.Timber

class CoinMonitoringApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(CoinMonitoringDebugTree())
    }

    private class CoinMonitoringDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String =
        "${element.fileName}:${element.lineNumber}:${element.methodName}"
    }

}
