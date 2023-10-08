package dev.dontsu.coinmonitoring.data.repository

import dev.dontsu.coinmonitoring.data.network.CoinApi
import dev.dontsu.coinmonitoring.data.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinRepository {

    private val client = RetrofitInstance.getInstance().create(CoinApi::class.java)

    suspend fun getCurrentCoins() = withContext(Dispatchers.IO) {
        client.getCurrentCoins()
    }

}
