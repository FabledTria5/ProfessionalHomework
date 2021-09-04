package com.example.professionalhomework.presentation.adapters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.WordItem
import com.example.professionalhomework.R
import com.example.professionalhomework.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val items: List<WordItem>,
) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WordItem) {
            binding.tvWord.text = item.word
            binding.tvTranslation.text = item.translation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        ItemHistoryBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.count()
}