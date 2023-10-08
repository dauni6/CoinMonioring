package dev.dontsu.coinmonitoring.ui.select

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.databinding.ActivitySelectBinding
import dev.dontsu.coinmonitoring.ui.base.BaseActivity

class SelectActivity : BaseActivity<ActivitySelectBinding, Nothing>(ActivitySelectBinding::inflate) {

    companion object {

        fun createIntent(context: Context): Intent = Intent(context, SelectActivity::class.java)

    }

}
