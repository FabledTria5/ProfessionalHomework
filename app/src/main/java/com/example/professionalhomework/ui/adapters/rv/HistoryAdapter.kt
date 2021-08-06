package com.example.professionalhomework.ui.adapters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.professionalhomework.R
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import com.example.professionalhomework.databinding.ItemHistoryBinding
import com.example.professionalhomework.ui.fragments.history.OnAudioClickListener
import com.example.professionalhomework.utils.Extensions.enable

class HistoryAdapter(
    private val items: List<WordWithMeanings>,
    private val onAudioClickListener: OnAudioClickListener
) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WordWithMeanings) {
            binding.tvWord.text = item.word.word
            binding.tvDescription.text = item.meanings[0].definition

            if (item.word.pronunciation != null) {
                binding.btnPlay.enable()
                binding.btnPlay.setOnClickListener {
                    onAudioClickListener.onAudioClick(item.word.pronunciation)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        ItemHistoryBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.count()
}