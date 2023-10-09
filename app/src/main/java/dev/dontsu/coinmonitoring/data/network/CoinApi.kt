package dev.dontsu.coinmonitoring.data.network

import dev.dontsu.coinmonitoring.data.model.reponse.CurrentPriceListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CoinApi {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoins(): CurrentPriceListResponse

}
