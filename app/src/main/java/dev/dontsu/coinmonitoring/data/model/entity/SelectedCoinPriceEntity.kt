package dev.dontsu.coinmonitoring.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity(tableName = "selected_coin_price_table")
data class SelectedCoinPriceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinName: String,
    val transactionDate: String,
    val type: String,
    val unitsTraded: String,
    val price: String,
    val total: String,
    val timestamp: Date
)

class DataConverters {

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

}