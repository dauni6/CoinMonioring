package dev.dontsu.coinmonitoring.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>(
    inflate: Inflate<VB>
): BaseViewModelFragment<VB, ViewModel>(inflate) {

    override val viewModel: ViewModel? = null

}
