package com.example.professionalhomework.ui.adapters.rv.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.professionalhomework.databinding.ItemMeaningBinding

class MeaningAdapter(private val presenter: MeaningsPresenter) :
    RecyclerView.Adapter<MeaningAdapter.MeaningViewHolderImpl>() {

    inner class MeaningViewHolderImpl(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root), MeaningViewHolder {

        override fun bindMeaning(partOfSpeech: String, meaning: String) {
            binding.tvPartOfSpeech.text = partOfSpeech
            binding.tvMeaning.text = meaning
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeaningViewHolderImpl(
        ItemMeaningBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: MeaningViewHolderImpl, position: Int) =
        presenter.bindView(holder, position)

    override fun getItemCount() = presenter.getCount()

}