package dev.dontsu.coinmonitoring.ui.intro

import androidx.navigation.Navigation
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.databinding.FragmentIntroFirstBinding
import dev.dontsu.coinmonitoring.ui.base.BaseFragment

class IntroFirstFragment : BaseFragment<FragmentIntroFirstBinding, Nothing>(FragmentIntroFirstBinding::inflate) {

    override fun initListeners() = with(binding) {

        nextBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_introFirstFragment_to_introSecondFragment)
        }

    }

}
