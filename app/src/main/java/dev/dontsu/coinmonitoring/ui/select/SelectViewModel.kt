package dev.dontsu.coinmonitoring.ui.select

import androidx.lifecycle.viewModelScope
import dev.dontsu.coinmonitoring.data.repository.CoinRepository
import dev.dontsu.coinmonitoring.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectViewModel: BaseViewModel() {

    private val coinRepository = CoinRepository()

    fun getCurrentCoins() = viewModelScope.launch {
        val result = coinRepository.getCurrentCoins()

        Timber.d("result = $result")

    }


}
