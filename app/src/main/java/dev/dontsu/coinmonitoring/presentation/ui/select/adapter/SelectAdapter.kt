package dev.dontsu.coinmonitoring.presentation.ui.select.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.dontsu.coinmonitoring.R
import dev.dontsu.coinmonitoring.data.model.CurrentPriceResult
import dev.dontsu.coinmonitoring.databinding.ItemIntroCoinBinding

class SelectAdapter(private val onLikeClick: (CurrentPriceResult, Int) -> Unit) : RecyclerView.Adapter<SelectAdapter.SelectViewHolder>() {

    private val items = arrayListOf<CurrentPriceResult>()
    private val selectedCoins = arrayListOf<String>()

    fun getSelectedCoins() = selectedCoins

    @SuppressLint("NotifyDataSetChanged")
    fun setNewItemList(list: List<CurrentPriceResult>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        val binding = ItemIntroCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectViewHolder(
            binding = binding,
            onLikeClick = onLikeClick
        )
    }

    override fun onBindViewHolder(
        holder: SelectViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            holder.bindLike(items[position])
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SelectViewHolder(
        private val binding: ItemIntroCoinBinding,
        onLikeClick: (CurrentPriceResult, Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var coin: CurrentPriceResult

        init {

            binding.likeBtn.setOnClickListener {
                if (selectedCoins.contains(coin.coinName)) {
                    selectedCoins.remove(coin.coinName)
                } else {
                    selectedCoins.add(coin.coinName)
                }
                onLikeClick.invoke(coin, adapterPosition)
            }

        }

        fun bind(data: CurrentPriceResult) = with(binding) {
            coin = data
            coinName.text = data.coinName
            val fluctate24H = data.coinInfo.fluctate24H
            if (fluctate24H.contains("-")) {
                coinPriceUpDown.text = "하락입니다."
                coinPriceUpDown.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue114))
            } else {
                coinPriceUpDown.text = "상승입니다."
                coinPriceUpDown.setTextColor(ContextCompat.getColor(itemView.context, R.color.red114))
            }

            if (data.isLiked) {
                likeBtn.setImageResource(R.drawable.like_red)
            } else {
                likeBtn.setImageResource(R.drawable.like_grey)
            }

        }

        fun bindLike(data: CurrentPriceResult) = with(binding) {
            data.isLiked = !data.isLiked
            if (data.isLiked) {
                likeBtn.setImageResource(R.drawable.like_red)
            } else {
                likeBtn.setImageResource(R.drawable.like_grey)
            }
        }

    }

}
