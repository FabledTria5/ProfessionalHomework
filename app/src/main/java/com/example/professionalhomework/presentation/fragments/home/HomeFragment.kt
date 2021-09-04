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
import com.example.professionalhomework.presentation.di.injectDependencies
import com.example.professionalhomework.presentation.entities.AppState
import com.example.professionalhomework.utils.ConnectivityLiveData
import com.example.professionalhomework.utils.Extensions.capitalize
import com.example.professionalhomework.utils.Extensions.extractText
import com.example.professionalhomework.utils.Extensions.hide
import com.example.professionalhomework.utils.Extensions.show
import com.example.professionalhomework.utils.Extensions.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.createScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class HomeFragment : BaseFragment<AppState>(R.layout.fragment_home), AndroidScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

    private lateinit var meaningAdapter: MeaningAdapter

    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()

    private val connectionSnackBar by lazy {
        Snackbar.make(
            binding.root,
            "No internet connection",
            Snackbar.LENGTH_INDEFINITE
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        setupUi()
        setupListeners()
    }

    override fun setupUi() {
        viewModel.subscribe().observe(viewLifecycleOwner, ::renderData)

        binding.rvSynonyms.apply {
            meaningAdapter = MeaningAdapter()
            adapter = meaningAdapter
        }

        ConnectivityLiveData(
            application = requireActivity().application
        ).observe(viewLifecycleOwner) { isAvailable ->
            when (isAvailable) {
                true -> connectionSnackBar.dismiss()
                false -> connectionSnackBar.show()
            }
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
        binding.ivWordImage.load(dataModel.data.imagePath) {
            placeholder(R.drawable.placeholder)
        }
        hideLoading()
    }
}