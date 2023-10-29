package dev.dontsu.coinmonitoring.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dontsu.coinmonitoring.data.model.entity.SelectedCoinPriceEntity

@Dao
interface SelectedCoinPriceDAO {

    @Query("SELECT * FROM selected_coin_price_table")
    fun getAllCoinPrices(): List<SelectedCoinPriceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: SelectedCoinPriceEntity)

    @Query("SELECT * FROM selected_coin_price_table WHERE coinName = :coinName")
    fun getOneCoinPrice(coinName: String): List<SelectedCoinPriceEntity>

}
