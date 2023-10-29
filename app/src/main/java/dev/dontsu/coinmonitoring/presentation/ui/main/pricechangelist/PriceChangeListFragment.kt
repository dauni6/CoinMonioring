package dev.dontsu.coinmonitoring.presentation.ui.main.pricechangelist

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import dev.dontsu.coinmonitoring.databinding.FragmentPriceChangeBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseFragment
import dev.dontsu.coinmonitoring.presentation.ui.main.MainViewModel
import dev.dontsu.coinmonitoring.presentation.ui.main.pricechangelist.adapter.PriceUpDownAdapter

class PriceChangeListFragment : BaseFragment<FragmentPriceChangeBinding>(FragmentPriceChangeBinding::inflate) {

    override val viewModel: MainViewModel by activityViewModels()

    override fun initDatas() {
        viewModel.getAllSelectedCOinPrice()
    }

    override fun initObservers() = with(viewModel) {
        changed15mins.observe(viewLifecycleOwner) {
            val upDownAdapter = PriceUpDownAdapter(it)
            binding.price15m.adapter = upDownAdapter
        }

        changed30mins.observe(viewLifecycleOwner) {
            val upDownAdapter = PriceUpDownAdapter(it)
            binding.price30m.adapter = upDownAdapter
        }

        changed45mins.observe(viewLifecycleOwner) {
            val upDownAdapter = PriceUpDownAdapter(it)
            binding.price45m.adapter = upDownAdapter
        }

    }


}
