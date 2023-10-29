package dev.dontsu.coinmonitoring.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.databinding.ActivityMainBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseActivity
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseViewModelActivity
import dev.dontsu.coinmonitoring.presentation.ui.setting.SettingActivity

class MainActivity : BaseViewModelActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMain) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMain.setupWithNavController(navController)

    }

    override fun initListeners() = with(binding) {
        setting.setOnClickListener {
            SettingActivity.startActivity(this@MainActivity)
        }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }

}
