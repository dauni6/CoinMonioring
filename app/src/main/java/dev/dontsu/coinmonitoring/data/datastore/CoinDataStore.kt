package dev.dontsu.coinmonitoring.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dev.dontsu.coinmonitoring.CoinMonitoringApplication

class CoinDataStore {

    private val context = CoinMonitoringApplication.context()
    private val mDataStore: DataStore<Preferences> = context.dataStore
    private val isFirstUser = booleanPreferencesKey("isFirstUser")

    suspend fun saveFirstUser() {
        mDataStore.edit {  preferences ->
            preferences[isFirstUser] = true
        }
    }

    suspend fun isFirstUser() : Boolean {
        var firstFlag = false
        mDataStore.edit {  preferences ->
            firstFlag = preferences[isFirstUser] ?: false
        }
        return firstFlag
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_pref")
    }

}
