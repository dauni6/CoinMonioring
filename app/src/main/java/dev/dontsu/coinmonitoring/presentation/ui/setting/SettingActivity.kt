package dev.dontsu.coinmonitoring.presentation.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.databinding.ActivitySettingBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseActivity
import dev.dontsu.coinmonitoring.service.PriceForegroundService

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) {

    override fun initListeners() = with(binding) {
        startForeground.setOnClickListener {
            val intent = Intent(this@SettingActivity, PriceForegroundService::class.java)
            intent.action = "START"
            startService(intent)
        }

        stopForeground.setOnClickListener {
            val intent = Intent(this@SettingActivity, PriceForegroundService::class.java)
            intent.action = "STOP"
            startService(intent)
        }

    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }

    }

}
