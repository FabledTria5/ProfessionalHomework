package com.example.professionalhomework.ui.activities.main

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.databinding.ActivityMainBinding
import com.example.professionalhomework.ui.adapters.rv.word.MeaningAdapter
import com.example.professionalhomework.ui.base.di.BaseDaggerActivity
import com.example.professionalhomework.utils.Extensions.disable
import com.example.professionalhomework.utils.Extensions.enable
import com.example.professionalhomework.utils.Extensions.hide
import com.example.professionalhomework.utils.Extensions.show
import com.google.android.material.tabs.TabLayout
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : BaseDaggerActivity(), MainView {

    @Inject
    lateinit var mainPresenterFactory: MainPresenterFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var meaningAdapter: MeaningAdapter

    private var mediaPlayer: MediaPlayer? = null

    private val mainPresenter by moxyPresenter {
        mainPresenterFactory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initialize()
        setupListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun enableAudio() = binding.btnPlay.enable()

    override fun disableAudio() = binding.btnPlay.disable()

    override fun updateWord(word: Word) {
        binding.tvWord.text = word.word
        meaningAdapter.notifyDataSetChanged()
    }

    override fun updateState(state: AppState) {
        when (state) {
            is AppState.Error -> mainPresenter.onGetWordError(state)
            is AppState.Loading -> showLoading()
            is AppState.Success -> {
                state.data?.let { mainPresenter.onGetWordSuccess(it) }
            }
        }
    }

    override fun playWord(wordPronunciation: String) {
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(wordPronunciation)
            setOnPreparedListener { player -> player.start() }
            prepareAsync()
        }
    }

    override fun showError() {
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

    override fun hideLoading() {
        binding.btnPlay.enable()
        binding.progressIndicator.hide()
        binding.groupResult.show()
    }

    private fun initialize() {
        binding.rvMeanings.apply {
            meaningAdapter = MeaningAdapter(mainPresenter.meaningsPresenter)
            adapter = meaningAdapter
        }
    }

    private fun setupListeners() {
        binding.tilSearchLayout.setEndIconOnClickListener {
            mainPresenter.onWordSubmitted(binding.tieSearchView.text.toString())
        }

        binding.btnPlay.setOnClickListener {
            mainPresenter.onPlayClicked()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let(mainPresenter::onLanguageSelected)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }
}