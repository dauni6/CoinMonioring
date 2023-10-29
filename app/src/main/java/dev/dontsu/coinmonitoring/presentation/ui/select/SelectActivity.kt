package dev.dontsu.coinmonitoring.presentation.ui.select

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.databinding.ActivitySelectBinding
import dev.dontsu.coinmonitoring.presentation.ui.base.BaseViewModelActivity
import dev.dontsu.coinmonitoring.presentation.ui.main.MainActivity
import dev.dontsu.coinmonitoring.presentation.ui.select.adapter.SelectAdapter

class SelectActivity : BaseViewModelActivity<ActivitySelectBinding, SelectViewModel>(ActivitySelectBinding::inflate) {

    override val viewModel: SelectViewModel by viewModels()

    private val onItemClick: ((CurrentPriceResult, Int) -> Unit) = { coin, position ->
        selectAdapter.notifyItemChanged(position, coin)
    }

    private val selectAdapter by lazy { SelectAdapter(onItemClick) }

    override fun initViews() = with(binding) {
        rvSelectCoin.adapter = selectAdapter
    }

    override fun initListeners() = with(binding) {

        viewModel.getCurrentCoins()

        tvSelectFinish.setOnClickListener {
            viewModel.saveFirstUser()
            viewModel.saveSelectedCoins(selectAdapter.getSelectedCoins())
        }

    }

    override fun initObservers() = with(viewModel) {
        currentPriceResultLiveData.observe(this@SelectActivity) {
            selectAdapter.setNewItemList(it)
        }

        viewModel.completedSave.observe(this@SelectActivity) { isCompleted ->
            if (isCompleted) {
                MainActivity.startActivity(this@SelectActivity)
            }
        }
    }

    companion object {

        fun createIntent(context: Context): Intent = Intent(context, SelectActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

    }

}
