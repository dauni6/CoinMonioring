package dev.dontsu.coinmonitoring.presentation.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.dontsu.coinmonitoring.data.datastore.CoinDataStore
import dev.dontsu.coinmonitoring.data.model.CurrentPrice
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import dev.dontsu.coinmonitoring.data.repository.CoinRepository
import dev.dontsu.coinmonitoring.data.repository.DBRepository
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectViewModel: BaseViewModel() {

    private val coinRepository = CoinRepository()
    private val dbRepository = DBRepository()

    private val currentPriceResultList = arrayListOf<CurrentPriceResult>()

    private val _currentPriceResultLiveData = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResultLiveData: LiveData<List<CurrentPriceResult>> = _currentPriceResultLiveData

    private val _completedSave = MutableLiveData<Boolean>(false)
    val completedSave: LiveData<Boolean> = _completedSave

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

    fun saveFirstUser() = viewModelScope.launch {
        CoinDataStore().saveFirstUser()
    }

    fun saveSelectedCoins(selectedItemList: ArrayList<String>) = viewModelScope.launch(Dispatchers.IO) {

        currentPriceResultList.forEach { coin ->
            val selected = selectedItemList.contains(coin.coinName)
            val interestCoinEntity = InterestCoinEntity (
                0,
                coin.coinName,
                coin.coinInfo.openingPrice,
                coin.coinInfo.closingPrice,
                coin.coinInfo.minPrice,
                coin.coinInfo.maxPrice,
                coin.coinInfo.unitsTraded,
                coin.coinInfo.accTradeValue,
                coin.coinInfo.prevClosingPrice,
                coin.coinInfo.unitsTraded24H,
                coin.coinInfo.accTradeValue24H,
                coin.coinInfo.fluctate24H,
                coin.coinInfo.fluctateRate24H,
                selected
            )

            dbRepository.insertInterestCoin(interestCoinEntity)

        }

        withContext(Dispatchers.Main) {
            _completedSave.value = true
        }

    }

}
