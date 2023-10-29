package dev.dontsu.coinmonitoring.presentation.ui.main.coinlist

import androidx.fragment.app.activityViewModels
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import dev.dontsu.coinmonitoring.databinding.FragmentCoinListBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseFragment
import dev.dontsu.coinmonitoring.presentation.ui.main.MainViewModel
import dev.dontsu.coinmonitoring.presentation.ui.main.coinlist.adapter.CoinListAdapter

class CoinListFragment : BaseFragment<FragmentCoinListBinding>(FragmentCoinListBinding::inflate) {

    override val viewModel: MainViewModel by activityViewModels()

    /**
     * todo : Fragment 안에 상태를 가지지 말 것
     * */
    private val selectedList = arrayListOf<InterestCoinEntity>()
    private val unSelectedList = arrayListOf<InterestCoinEntity>()

    override fun initViews() {
        viewModel.getAllInterestCoins()
    }

    override fun initObservers(): Unit = with(viewModel) {
        selectedCoinList.observe(viewLifecycleOwner) { coins ->
            selectedList.clear()
            unSelectedList.clear()

            coins.forEach { coinEntity ->
                if (coinEntity.selected) {
                    selectedList.add(coinEntity)
                } else {
                    unSelectedList.add(coinEntity)
                }
            }

            initRecyclerView()
        }

    }

    private fun initRecyclerView() = with(binding) {
        val selectedAdapter = CoinListAdapter(selectedList) {
            viewModel.updateInterestCoin(selectedList[it])
        }
        selectedCoinRV.adapter = selectedAdapter


        val unselectedAdapter = CoinListAdapter(unSelectedList) {
            viewModel.updateInterestCoin(unSelectedList[it])
        }
        unSelectedCoinRV.adapter = unselectedAdapter
    }

}
