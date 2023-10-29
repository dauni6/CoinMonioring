package dev.dontsu.coinmonitoring.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.data.model.CurrentPrice
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.data.repository.CoinRepository
import dev.dontsu.coinmonitoring.presentation.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Random

class PriceForegroundService: Service() {

    private val coinRepository = CoinRepository()

    private val notificationID = 20231029

    lateinit var job: Job

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            "START" -> {
                job = CoroutineScope(Dispatchers.Default).launch {
                    while (true) {
                        startForeground(notificationID, makeNotification())
                        delay(3_000)
                    }
                }
            }

            "STOP" -> {
                job.cancel()
                try {
                    
                    // try catch 안 해저고 곧바로 끄면 앱 터짐
                    stopForeground(STOP_FOREGROUND_REMOVE)
                    stopSelf()
                } catch (e: Exception) {
                    
                }

            }
        }

        return START_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private suspend fun makeNotification(): Notification {
        val result = getAllCoins()

        val randomNum = Random().nextInt(result.size)

        val title = result[randomNum].coinName
        val content = result[randomNum].coinInfo.fluctate24H

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
            .setContentText("코인 이름 : $title")
            .setContentText("변동 가격 : $content")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "name"
            val descriptionText = "descriptionText"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
        return builder.build()
    }

    suspend fun getAllCoins(): List< CurrentPriceResult> {
        val result = coinRepository.getCurrentCoins()

        val currentPriceResultList = arrayListOf<CurrentPriceResult>()

        result.data.forEach { coin ->
            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[coin.key])
                val coinInfoFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)
                val currentPriceResult = CurrentPriceResult(coinName = coin.key, coinInfo = coinInfoFromJson)
                currentPriceResultList.add(currentPriceResult)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }
        }

        return currentPriceResultList
    }

}
