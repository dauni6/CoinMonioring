package dev.dontsu.coinmonitoring.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.dontsu.coinmonitoring.data.model.PriceUpDown
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import dev.dontsu.coinmonitoring.data.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()
    lateinit var selectedCoinList: LiveData<List<InterestCoinEntity>>

    private val _changed15mins: MutableLiveData<List<PriceUpDown>> = MutableLiveData<List<PriceUpDown>>()
    val changed15mins: LiveData<List<PriceUpDown>> = _changed15mins

    private val _changed30mins: MutableLiveData<List<PriceUpDown>> = MutableLiveData<List<PriceUpDown>>()
    val changed30mins: LiveData<List<PriceUpDown>> = _changed30mins

    private val _changed45mins: MutableLiveData<List<PriceUpDown>> = MutableLiveData<List<PriceUpDown>>()
    val changed45mins: LiveData<List<PriceUpDown>> = _changed45mins

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
    // 1. 관심있다고 선택한 코인 리스트를 가져와서
    // 2. 코인 리스트를 반복문을 통해 하나씩 가져와서
    // 3. 저장된 코인 가격 리스트를 가져와서
    // 4. 시간대마다 어떻게 변경되었는지를 알려주는 로직을 작성
    fun getAllSelectedCOinPrice() = viewModelScope.launch(Dispatchers.IO) {
        val selectedCoinPrices = dbRepository.getAllInterestSelectedCoins()
        val tempChanged15mins = arrayListOf<PriceUpDown>()
        val tempChanged30mins = arrayListOf<PriceUpDown>()
        val tempChanged45mins = arrayListOf<PriceUpDown>()

        selectedCoinPrices.forEach { coin ->
            val coinName = coin.coinName
            val selectCoinPrice = dbRepository.getOneSelectedCoinPrice(coinName = coinName).reversed()
            val size = selectCoinPrice.size
            if (size > 1) {
                // DB에 값이 2개 이상은 있다.
                // 현재와 15분전의 가격을 비교하려면 데이터가 2개는 있어야 함.
                val changedPrice = selectCoinPrice[0].price.toDouble() - selectCoinPrice[1].price.toDouble()
                val priceUpDown = PriceUpDown(
                    coinName = coinName,
                    upDownPrice = changedPrice.toString()
                )
                tempChanged15mins.add(priceUpDown)
            }

            if (size > 2) {
                // DB에 값이 3개 이상은 있다.
                // 현재와 30분전의 가격을 비교하려면 데이터가 3개는 있어야 함.
                val changedPrice = selectCoinPrice[0].price.toDouble() - selectCoinPrice[2].price.toDouble()
                val priceUpDown = PriceUpDown(
                    coinName = coinName,
                    upDownPrice = changedPrice.toString()
                )
                tempChanged30mins.add(priceUpDown)
            }

            if (size > 3) {
                // DB에 값이 4개 이상은 있다.
                // 현재와 45분전의 가격을 비교하려면 데이터가 4개는 있어야 함.
                val changedPrice = selectCoinPrice[0].price.toDouble() - selectCoinPrice[3].price.toDouble()
                val priceUpDown = PriceUpDown(
                    coinName = coinName,
                    upDownPrice = changedPrice.toString()
                )
                tempChanged45mins.add(priceUpDown)
            }

        }

        withContext(Dispatchers.Main) {
            _changed15mins.value = tempChanged15mins
            _changed30mins.value = tempChanged30mins
            _changed45mins.value = tempChanged45mins
        }
    }

}
