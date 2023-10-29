package dev.dontsu.coinmonitoring.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interest_coin_table")
data class InterestCoinEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinName: String,
    val openingPrice: String,
    val closingPrice: String,
    val minPrice: String,
    val maxPrice: String,
    val unitsTraded: String,
    val accTradeValue: String,
    val prevClosingPrice: String,
    val unitsTraded24H: String,
    val accTradeValue24H: String,
    val fluctate24H: String,
    val fluctateRate24H: String,
    var selected: Boolean
)