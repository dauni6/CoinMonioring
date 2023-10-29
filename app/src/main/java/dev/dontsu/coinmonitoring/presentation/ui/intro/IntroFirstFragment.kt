package dev.dontsu.coinmonitoring.presentation.ui.intro

import androidx.navigation.Navigation
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.databinding.FragmentIntroFirstBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseFragment
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseViewModelFragment

class IntroFirstFragment : BaseFragment<FragmentIntroFirstBinding>(FragmentIntroFirstBinding::inflate) {

    override fun initListeners() = with(binding) {

        nextBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_introFirstFragment_to_introSecondFragment)
        }

    }

}
