package com.example.professionalhomework.ui.activities.main

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.professionalhomework.DictionaryApplication
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.databinding.ActivityMainBinding
import com.example.professionalhomework.ui.adapters.rv.word.MeaningAdapter
import com.example.professionalhomework.ui.base.view.BaseActivity
import com.example.professionalhomework.utils.Extensions.disable
import com.example.professionalhomework.utils.Extensions.enable
import com.example.professionalhomework.utils.Extensions.hide
import com.example.professionalhomework.utils.Extensions.show
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var meaningAdapter: MeaningAdapter

    private var mediaPlayer: MediaPlayer? = null
    private var wordAudio: String? = null

    val viewModel: MainViewModel by viewModel()

    private val tabListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.position?.let(viewModel::onLanguageChanged)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initialize()
        setupListeners()
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> showError()
            is AppState.Loading -> showLoading()
            is AppState.Success -> setResult(dataModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    private fun initialize() {
        viewModel.subscribe().observe(this, ::renderData)

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
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        binding.tvErrorMessage.hide()
        binding.groupResult.hide()
        binding.progressIndicator.show()
    }

    private fun hideLoading() {
        binding.btnPlay.enable()
        binding.progressIndicator.hide()
        binding.groupResult.show()
    }
}