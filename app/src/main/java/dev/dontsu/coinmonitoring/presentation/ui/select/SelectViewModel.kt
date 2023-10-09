package dev.dontsu.coinmonitoring.presentation.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.dontsu.coinmonitoring.data.model.CurrentPrice
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.data.repository.CoinRepository
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SelectViewModel: BaseViewModel() {

    private val coinRepository = CoinRepository()

    private val currentPriceResultList = arrayListOf<CurrentPriceResult>()

    private val _currentPriceResultLiveData = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResultLiveData: LiveData<List<CurrentPriceResult>> = _currentPriceResultLiveData

    fun getCurrentCoins() = viewModelScope.launch {
        val result = coinRepository.getCurrentCoins()

        result.data.forEach { coin ->
            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[coin.key])
                val coinInfoFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)
                val currentPriceResult = CurrentPriceResult(coinName = coin.key, coinInfo = coinInfoFromJson)
                currentPriceResultList.add(currentPriceResult)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }
        }

        _currentPriceResultLiveData.value = currentPriceResultList
    }

}
