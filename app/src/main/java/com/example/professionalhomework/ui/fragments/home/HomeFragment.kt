package com.example.professionalhomework.ui.fragments.home

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.findNavController
import com.example.professionalhomework.R
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.databinding.FragmentHomeBinding
import com.example.professionalhomework.ui.adapters.rv.MeaningAdapter
import com.example.professionalhomework.ui.base.view.BaseFragment
import com.example.professionalhomework.utils.Extensions.disable
import com.example.professionalhomework.utils.Extensions.enable
import com.example.professionalhomework.utils.Extensions.hide
import com.example.professionalhomework.utils.Extensions.show
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<AppState>() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var meaningAdapter: MeaningAdapter

    val viewModel: HomeViewModel by viewModel()

    private var mediaPlayer: MediaPlayer? = null
    private var wordAudio: String? = null

    private val tabListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.position?.let(viewModel::onLanguageChanged)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        setupListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> showError()
            is AppState.Loading -> showLoading()
            is AppState.Success -> setResult(dataModel)
        }
    }

    private fun initialize() {
        viewModel.subscribe().observe(viewLifecycleOwner, ::renderData)

        binding.rvMeanings.apply {
            meaningAdapter = MeaningAdapter()
            adapter = meaningAdapter
        }
    }

    private fun setupListeners() {
        binding.tilSearchLayout.setEndIconOnClickListener {
            viewModel.getData(binding.tieSearchView.text.toString())
        }

        binding.btnPlay.setOnClickListener { playWord() }

        binding.tabLayout.addOnTabSelectedListener(tabListener)

        binding.btnHistory.setOnClickListener {
            requireView().findNavController().navigate(R.id.openHostoryFragment)
        }
    }

    private fun setResult(dataModel: AppState.Success) {
        binding.tvWord.text = dataModel.data.word.word
        meaningAdapter.updateList(dataModel.data.meanings)
        hideLoading()
        if (dataModel.data.word.pronunciation != null) {
            wordAudio = dataModel.data.word.pronunciation
            enableAudio()
        } else disableAudio()
    }

    private fun enableAudio() = binding.btnPlay.enable()

    private fun disableAudio() = binding.btnPlay.disable()

    private fun playWord() {
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(wordAudio)
            setOnPreparedListener { player -> player.start() }
            prepareAsync()
        }
    }

    private fun showError() {
        binding.btnPlay.disable()
        binding.progressIndicator.hide()
        binding.tvErrorMessage.show()
    }

    private fun showLoading() {
        hideKeyboard()
        binding.tvErrorMessage.hide()
        binding.groupResult.hide()
        binding.progressIndicator.show()
    }

    private fun hideKeyboard() =
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
            it.hideSoftInputFromWindow(view?.windowToken, 0)
        }

    private fun hideLoading() {
        binding.btnPlay.enable()
        binding.progressIndicator.hide()
        binding.groupResult.show()
    }
}