package dev.dontsu.coinmonitoring.presentation.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dontsu.coinmonitoring.data.datastore.CoinDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroViewModel: ViewModel() {

    private val _isFirstUser = MutableLiveData<Boolean>()
    val isFirstUser: LiveData<Boolean> = _isFirstUser

    fun checkFirstFlag() = viewModelScope.launch {
        delay(2000)
        val isFirstUser = CoinDataStore().isFirstUser()
        _isFirstUser.value = isFirstUser

    }

}
