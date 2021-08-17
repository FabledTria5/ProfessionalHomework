package com.example.professionalhomework.presentation.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.professionalhomework.R
import com.example.professionalhomework.databinding.FragmentHomeBinding
import com.example.professionalhomework.presentation.adapters.rv.MeaningAdapter
import com.example.professionalhomework.presentation.base.view.BaseFragment
import com.example.professionalhomework.presentation.di.loadHomeModule
import com.example.professionalhomework.presentation.entities.AppState
import com.example.professionalhomework.utils.Extensions.capitalize
import com.example.professionalhomework.utils.Extensions.extractText
import com.example.professionalhomework.utils.Extensions.hide
import com.example.professionalhomework.utils.Extensions.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<AppState>(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var meaningAdapter: MeaningAdapter

    private val viewModel: HomeViewModel by viewModel()
    private fun injectFeatures() = loadHomeModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        injectFeatures()

        setupUi()
        setupListeners()
    }

    override fun setupUi() {
        viewModel.subscribe().observe(viewLifecycleOwner, ::renderData)

        binding.rvSynonyms.apply {
            meaningAdapter = MeaningAdapter()
            adapter = meaningAdapter
        }
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> showError()
            is AppState.Loading -> showLoading()
            is AppState.SearchWordSuccess -> setResult(dataModel)
            else -> Unit
        }
    }

    override fun showError() {
        binding.progressIndicator.hide()
        binding.tvErrorMessage.show()
    }

    override fun showLoading() {
        hideKeyboard()
        binding.tvErrorMessage.hide()
        binding.groupResult.hide()
        binding.progressIndicator.show()
    }

    override fun hideLoading() {
        binding.progressIndicator.hide()
        binding.groupResult.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun hideKeyboard() =
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
            it.hideSoftInputFromWindow(view?.windowToken, 0)
        }

    private fun setupListeners() {
        binding.tilSearchLayout.setEndIconOnClickListener {
            viewModel.getData(binding.tieSearchView.extractText())
        }

        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.openHistoryFragment)
        }

        binding.tieSearchView.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getData(view.extractText())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setResult(dataModel: AppState.SearchWordSuccess) {
        binding.tvWord.text = dataModel.data.word.capitalize()
        binding.tvWordTranslation.text = dataModel.data.translation
        meaningAdapter.updateList(dataModel.data.synonyms)
        binding.ivWordImage.load("https:${dataModel.data.imagePath}") {
            placeholder(R.drawable.placeholder)
        }
        hideLoading()
    }
}