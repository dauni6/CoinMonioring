package dev.dontsu.coinmonitoring.ui.select

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import dev.dontsu.coinmonitoring.databinding.ActivitySelectBinding
import dev.dontsu.coinmonitoring.ui.base.BaseViewModelActivity

class SelectActivity : BaseViewModelActivity<ActivitySelectBinding, SelectViewModel>(ActivitySelectBinding::inflate) {

    override val viewModel: SelectViewModel by viewModels()

    companion object {

        fun createIntent(context: Context): Intent = Intent(context, SelectActivity::class.java)

    }

}
