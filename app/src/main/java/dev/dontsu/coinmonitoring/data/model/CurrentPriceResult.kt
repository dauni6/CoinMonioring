package dev.dontsu.coinmonitoring.data.model

data class CurrentPriceResult(
    val coinName: String,
    val coinInfo: CurrentPrice,
    var isLiked: Boolean = false
)