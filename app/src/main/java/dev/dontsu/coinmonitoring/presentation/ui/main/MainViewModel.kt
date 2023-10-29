package dev.dontsu.coinmonitoring.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import dev.dontsu.coinmonitoring.data.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()
    lateinit var selectedCoinList: LiveData<List<InterestCoinEntity>>

    // CoinListFragment
    fun getAllInterestCoins() = viewModelScope.launch {
        val coinList = dbRepository.getAllInterestCoins().asLiveData()
        withContext(Dispatchers.Main) {
            selectedCoinList = coinList
        }
    }

    fun updateInterestCoin(entity: InterestCoinEntity) = viewModelScope.launch(Dispatchers.IO) {
        entity.selected = !entity.selected
        dbRepository.updateInterestCoin(entity)
    }

    // PriceChangeFragment

}
