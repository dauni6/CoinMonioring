package dev.dontsu.coinmonitoring.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.dontsu.coinmonitoring.data.model.RecentPriceList
import dev.dontsu.coinmonitoring.data.model.entity.SelectedCoinPriceEntity
import dev.dontsu.coinmonitoring.data.repository.CoinRepository
import dev.dontsu.coinmonitoring.data.repository.DBRepository
import timber.log.Timber
import java.util.Calendar
import java.util.Date

// 최근 거래된 코인 가격 내역을 가져오는 WorkManager

// 1. 관심있어하는 코인 리스트를 가져와서
// 2. 관심있는 코인 각각의 가격 변동 정보를 가져와서 (NEW API)
// 3. 관심있는 코인 각각의 가격 변동 정보 DB에 저장

class GetCoinPriceRecentContractedWorkManager(
    val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dbRepository = DBRepository()
    private val coinRepository = CoinRepository()

    override suspend fun doWork(): Result {
        getAllInterestSelectedCoins()

        return Result.success()
    }

    private suspend fun getAllInterestSelectedCoins() {
        val selectedCoins = dbRepository.getAllInterestSelectedCoins()
        val timestamp = Calendar.getInstance().time

        selectedCoins.forEach { coin ->
            val recentCoinPrices = coinRepository.getRecentCoinPrices(coin.coinName)
            saveSelectedCoinPrice(
                coinName = coin.coinName,
                recentPriceList = recentCoinPrices,
                timestamp = timestamp
            )
        }

    }

    fun saveSelectedCoinPrice(
        coinName: String,
        recentPriceList: RecentPriceList,
        timestamp: Date
    ) {
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            id = 0,
            coinName = coinName,
            transactionDate = recentPriceList.data[0].transactionDate,
            type = recentPriceList.data[0].type,
            unitsTraded = recentPriceList.data[0].unitsTraded,
            price = recentPriceList.data[0].price,
            total  =recentPriceList.data[0].total,
            timestamp = timestamp
        )
        dbRepository.insertCoinPrice(selectedCoinPriceEntity)
    }

}
