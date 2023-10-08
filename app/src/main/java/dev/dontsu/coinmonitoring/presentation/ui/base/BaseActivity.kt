package dev.dontsu.coinmonitoring.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(
    inflate: Inflate<VB>
): BaseViewModelActivity<VB, ViewModel>(inflate) {

    /**
     * viewModel을 사용하려면 BaseViewModelActivity를 사용하세요.
     * */
    override val viewModel: ViewModel? = null

}