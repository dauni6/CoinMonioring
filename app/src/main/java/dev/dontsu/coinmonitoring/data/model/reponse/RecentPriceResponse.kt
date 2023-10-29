package dev.dontsu.coinmonitoring.data.model.reponse

import com.google.gson.annotations.SerializedName

data class RecentPriceResponse(
    @SerializedName("transaction_date")
    val transactionDate: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("units_traded")
    val unitsTraded: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("total")
    val total: String
)
