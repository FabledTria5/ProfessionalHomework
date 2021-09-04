package com.example.historyfeature.ui

import android.os.Bundle
import android.view.View
import com.example.historyfeature.R
import com.example.historyfeature.databinding.FragmentHistoryBinding
import com.example.historyfeature.di.loadHistoryModule
import com.example.professionalhomework.presentation.adapters.rv.HistoryAdapter
import com.example.professionalhomework.presentation.base.view.BaseFragment
import com.example.professionalhomework.presentation.entities.AppState
import com.example.professionalhomework.utils.Extensions.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState>(R.layout.fragment_history) {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModel()
    private fun injectFeatures() = loadHistoryModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)

        injectFeatures()

        setupUi()
    }

    override fun setupUi() {
        viewModel.subscribe().observe(viewLifecycleOwner, ::renderData)
        viewModel.getData()
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> showError()
            is AppState.Loading -> showLoading()
            is AppState.LoadHistorySuccess -> setResult(dataModel)
            else -> Unit
        }
    }

    override fun showLoading() = binding.progressIndicator.show()

    override fun hideLoading() = binding.progressIndicator.hide()

    override fun showError() {
        binding.tvErrorMessage.show()
        hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setResult(dataModel: AppState.LoadHistorySuccess) {
        binding.rvHistory.apply {
            adapter = HistoryAdapter(dataModel.data)
            show()
        }
        hideLoading()
    }
}