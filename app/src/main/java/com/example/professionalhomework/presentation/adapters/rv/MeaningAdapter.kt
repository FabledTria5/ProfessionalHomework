package com.example.professionalhomework.presentation.adapters.rv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.professionalhomework.R
import com.example.professionalhomework.databinding.ItemMeaningBinding

class MeaningAdapter : RecyclerView.Adapter<MeaningAdapter.MeaningViewHolderImpl>() {

    private val itemList = mutableListOf<Pair<String, String>>()

    inner class MeaningViewHolderImpl(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSynonym(item: Pair<String, String>) {
            binding.tvOriginalWord.text = item.first
            binding.tvTranslation.text = item.second
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeaningViewHolderImpl(
        ItemMeaningBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_meaning,
                parent,
                false
            )
        )
    )

    override fun onBindViewHolder(holder: MeaningViewHolderImpl, position: Int) =
        holder.bindSynonym(itemList[position])

    override fun getItemCount() = itemList.count()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<Pair<String, String>>) {
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }
}