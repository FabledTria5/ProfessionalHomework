package com.example.professionalhomework.ui.fragments.history

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.databinding.FragmentHistoryBinding
import com.example.professionalhomework.ui.adapters.rv.HistoryAdapter
import com.example.professionalhomework.ui.base.view.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState>() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private var mediaPlayer: MediaPlayer? = null

    private val viewModel: HistoryViewModel by viewModel()

    private val onAudioClickListener = object : OnAudioClickListener {
        override fun onAudioClick(audioUrl: String) {
            mediaPlayer?.release()

            mediaPlayer = MediaPlayer().apply {
                setDataSource(audioUrl)
                setOnPreparedListener { player -> player.start() }
                prepareAsync()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.subscribe().observe(viewLifecycleOwner, ::renderData)
        viewModel.getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> TODO()
            is AppState.Loading -> TODO()
            is AppState.ListSuccess -> setResult(dataModel)
            else -> TODO()
        }
    }

    private fun setResult(dataModel: AppState.ListSuccess) {
        binding.rvHistory.apply {
            adapter = HistoryAdapter(dataModel.data, onAudioClickListener)
        }
    }
}