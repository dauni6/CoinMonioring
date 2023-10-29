package dev.dontsu.coinmonitoring.data.repository

import dev.dontsu.coinmonitoring.CoinMonitoringApplication
import dev.dontsu.coinmonitoring.data.db.CoinPriceDatabase
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import dev.dontsu.coinmonitoring.data.model.entity.SelectedCoinPriceEntity

class DBRepository {

    val context = CoinMonitoringApplication.context()
    val db = CoinPriceDatabase.getDatabase(context)

    fun getAllInterestCoins() = db.interestCoinDAO().getAllData()

    fun insertInterestCoin(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().insert(interestCoinEntity = interestCoinEntity)

    fun updateInterestCoin(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().update(interestCoinEntity = interestCoinEntity)

    fun getAllInterestSelectedCoins() = db.interestCoinDAO().getSelectedCoins()

    fun getAllCoinPrices() = db.selectedCoinPriceDAO().getAllCoinPrices()

    fun insertCoinPrice(entity: SelectedCoinPriceEntity) = db.selectedCoinPriceDAO().insert(entity)

    fun getOneSelectedCoinPrice(coinName: String) = db.selectedCoinPriceDAO().getOneCoinPrice(coinName)

}
