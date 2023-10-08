package dev.dontsu.coinmonitoring.ui.select

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.dontsu.coinmonitoring.data.model.CurrentPrice
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.data.repository.CoinRepository
import dev.dontsu.coinmonitoring.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SelectViewModel: BaseViewModel() {

    private val coinRepository = CoinRepository()

    private val _currentPriceResult = arrayListOf<CurrentPriceResult>()
    val currentPriceResult = _currentPriceResult as List<CurrentPriceResult>

    fun getCurrentCoins() = viewModelScope.launch {
        val result = coinRepository.getCurrentCoins()

        result.data.forEach { coin ->
            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[coin.key])
                val coinInfoFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)
                val currentPriceResult = CurrentPriceResult(coinName = coin.key, coinInfo = coinInfoFromJson)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }
        }
    }

}
