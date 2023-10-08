package dev.dontsu.coinmonitoring.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import dev.dontsu.coinmonitoring.ui.intro.IntroActivity

abstract class BaseViewModelActivity<VB : ViewBinding, VM : ViewModel>(
    private val inflate: Inflate<VB>
) : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM?

    override fun onCreate(savedInstanceState: Bundle?) {
        startSplashScreen()
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater, null, false)
        setContentView(binding.root)
        initObservers()
        initViews()
        initListeners()
    }

    private fun startSplashScreen() {
        if (this is IntroActivity) {
            installSplashScreen()
        }
    }

    open fun initViews() = Unit

    open fun initListeners() = Unit

    /**
     * this method will be used for Observing StateFlow<T>.
     * it’s recommended to call this API in the activity’s onCreate to avoid unexpected behaviors.
     * */
    open fun initObservers() = Unit

}
