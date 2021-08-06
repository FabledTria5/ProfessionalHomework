package com.example.professionalhomework.ui.adapters.rv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.professionalhomework.R
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.databinding.ItemMeaningBinding

class MeaningAdapter : RecyclerView.Adapter<MeaningAdapter.MeaningViewHolderImpl>() {

    private val itemList = mutableListOf<Meaning>()

    inner class MeaningViewHolderImpl(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMeaning(meaning: Meaning) {
            binding.tvPartOfSpeech.text = meaning.partOfSpeech
            binding.tvMeaning.text = meaning.definition
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeaningViewHolderImpl(
        ItemMeaningBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false)
        )
    )

    override fun onBindViewHolder(holder: MeaningViewHolderImpl, position: Int) =
        holder.bindMeaning(itemList[position])

    override fun getItemCount() = itemList.count()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<Meaning>) {
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }

}