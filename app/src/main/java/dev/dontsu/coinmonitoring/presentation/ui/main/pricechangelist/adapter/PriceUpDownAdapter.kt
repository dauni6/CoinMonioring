package dev.dontsu.coinmonitoring.presentation.ui.main.pricechangelist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.dontsu.coinmonitoring.data.model.PriceUpDown
import dev.dontsu.coinmonitoring.databinding.ItemPriceChangeBinding

class PriceUpDownAdapter(
    private val prices: List<PriceUpDown>
) : RecyclerView.Adapter<PriceUpDownAdapter.PriceUpDownViewHolder>() {

    override fun getItemCount(): Int = prices.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceUpDownViewHolder {
        val binding = ItemPriceChangeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceUpDownViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: PriceUpDownViewHolder, position: Int) {
        holder.bind(prices[position])
    }

    class PriceUpDownViewHolder(
        private val binding: ItemPriceChangeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(priceUpDown: PriceUpDown) = with(binding) {
            coinName.text = priceUpDown.coinName
            if (priceUpDown.upDownPrice.contains("-")) {
                coinPriceUpDown.text = "하락"
                coinPriceUpDown.setTextColor(Color.parseColor("#114fed"))
            } else{
                coinPriceUpDown.text = "상승"
                coinPriceUpDown.setTextColor(Color.parseColor("#ed2e11"))
            }
            price.text = priceUpDown.upDownPrice.split(".")[0]
        }

    }

}
