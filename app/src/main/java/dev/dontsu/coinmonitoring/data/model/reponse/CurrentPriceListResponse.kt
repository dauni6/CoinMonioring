package dev.dontsu.coinmonitoring.data.model.reponse

data class CurrentPriceListResponse(
    val status: String,
    val data: Map<String, Any>
) {

}