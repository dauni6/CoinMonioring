package dev.dontsu.coinmonitoring.ui.intro

import dev.dontsu.coinmonitoring.databinding.FragmentIntroSecondBinding
import dev.dontsu.coinmonitoring.ui.base.BaseFragment
import dev.dontsu.coinmonitoring.ui.select.SelectActivity

class IntroSecondFragment : BaseFragment<FragmentIntroSecondBinding, Nothing>(FragmentIntroSecondBinding::inflate) {

    override fun initListeners() = with(binding) {
        nextBtn.setOnClickListener {
           startActivity(SelectActivity.createIntent(requireContext()))
        }
    }

}
