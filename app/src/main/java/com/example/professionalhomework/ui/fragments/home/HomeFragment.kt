package com.example.professionalhomework.ui.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.findNavController
import coil.load
import com.example.professionalhomework.R
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.databinding.FragmentHomeBinding
import com.example.professionalhomework.ui.adapters.rv.MeaningAdapter
import com.example.professionalhomework.ui.base.view.BaseFragment
import com.example.professionalhomework.utils.Extensions.capitalize
import com.example.professionalhomework.utils.Extensions.hide
import com.example.professionalhomework.utils.Extensions.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<AppState>() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var meaningAdapter: MeaningAdapter

    val viewModel: HomeViewModel by viewModel()

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
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> showError()
            is AppState.Loading -> showLoading()
            is AppState.Success -> setResult(dataModel)
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

    private fun initialize() {
        viewModel.subscribe().observe(viewLifecycleOwner, ::renderData)

        binding.rvSynonyms.apply {
            meaningAdapter = MeaningAdapter()
            adapter = meaningAdapter
        }
    }

    private fun setupListeners() {
        binding.tilSearchLayout.setEndIconOnClickListener {
            viewModel.getData(binding.tieSearchView.text.toString().trim())
        }

        binding.btnHistory.setOnClickListener {
            requireView().findNavController().navigate(R.id.openHostoryFragment)
        }
    }

    private fun setResult(dataModel: AppState.Success) {
        binding.tvWord.text = dataModel.data.word.word.capitalize()
        binding.tvWordTranslation.text = dataModel.data.word.translation
        meaningAdapter.updateList(dataModel.data.synonyms)
        binding.ivWordImage.load("""https:${dataModel.data.word.image}""") {
            placeholder(R.drawable.placeholder)
        }
        hideLoading()
    }
}