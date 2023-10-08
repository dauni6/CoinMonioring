package dev.dontsu.coinmonitoring.data.model.reponse

data class CurrentPriceList(
    val status: String,
    val data: Map<String, Any>
) {

}