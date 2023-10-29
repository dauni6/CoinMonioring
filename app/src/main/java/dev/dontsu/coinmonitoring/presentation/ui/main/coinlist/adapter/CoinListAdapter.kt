package dev.dontsu.coinmonitoring.presentation.ui.main.coinlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.data.model.entity.InterestCoinEntity
import dev.dontsu.coinmonitoring.databinding.ItemMainCoinBinding

class CoinListAdapter(
    private val coins: List<InterestCoinEntity>,
    private val onLikeClick: (Int) -> Unit
): RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

    override fun getItemCount(): Int = coins.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemMainCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CoinViewHolder(binding = binding)
        binding.likeBtn.setOnClickListener {
            onLikeClick(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
       holder.bind(coins[position])
    }

    class CoinViewHolder(
        private val binding: ItemMainCoinBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: InterestCoinEntity) = with(binding) {
            coinName.text = coin.coinName
            if (coin.selected) {
                likeBtn.setImageResource(R.drawable.like_red)
            } else {
                likeBtn.setImageResource(R.drawable.like_grey)
            }
        }

    }


}
