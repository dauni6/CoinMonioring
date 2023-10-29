package dev.dontsu.coinmonitoring.data.model

import dev.dontsu.coinmonitoring.data.model.reponse.RecentPriceResponse

data class RecentPriceList(
    val status: String,
    val data: List<RecentPriceResponse>
)
