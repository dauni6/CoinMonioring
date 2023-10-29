package dev.dontsu.coinmonitoring.presentation.ui.intro

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.dontsu.coinmonitoring.databinding.ActivityIntroBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseViewModelActivity
import dev.dontsu.coinmonitoring.presentation.ui.extensions.toInvisible
import dev.dontsu.coinmonitoring.presentation.ui.extensions.toVisible
import dev.dontsu.coinmonitoring.presentation.ui.main.MainActivity

class IntroActivity : BaseViewModelActivity<ActivityIntroBinding, IntroViewModel>(ActivityIntroBinding::inflate) {

    override val viewModel: IntroViewModel by viewModels()

    override fun initViews() {
        viewModel.checkFirstFlag()
    }

    override fun initObservers() {
        viewModel.isFirstUser.observe(this) { isFirstUser ->
            if (isFirstUser) {
                MainActivity.startActivity(this@IntroActivity)
                finish()
                return@observe
            }
            binding.lottieAnimationView.toInvisible()
            binding.fragmentContainerView.toVisible()
        }

    }

}
