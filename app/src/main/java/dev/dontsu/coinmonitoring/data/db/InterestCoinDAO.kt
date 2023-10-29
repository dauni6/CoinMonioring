package dev.dontsu.coinmonitoring.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDAO {

    @Query("SELECT * FROM interest_coin_table")
    fun getAllData() : Flow<List<InterestCoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    @Update
    fun update(interestCoinEntity: InterestCoinEntity)

    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedCoins(selected: Boolean = true) : List<InterestCoinEntity>

}
