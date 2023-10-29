package dev.dontsu.coinmonitoring.data.network

import dev.dontsu.coinmonitoring.data.model.RecentPriceList
import dev.dontsu.coinmonitoring.data.model.reponse.CurrentPriceListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoins(): CurrentPriceListResponse

   // https://api.bithumb.com/
    @GET("public/transaction_history/{coin}_KRW")
    suspend fun getRecentCoinPrices(@Path("coin") coin: String): RecentPriceList

}
