package com.example.professionalhomework.presentation.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.professionalhomework.presentation.entities.AppState
import com.example.professionalhomework.databinding.FragmentHistoryBinding
import com.example.professionalhomework.presentation.adapters.rv.HistoryAdapter
import com.example.professionalhomework.presentation.base.view.BaseFragment
import com.example.professionalhomework.utils.Extensions.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState>() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModel()

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

    private fun setResult(dataModel: AppState.LoadHistorySuccess) {
        binding.rvHistory.apply {
            adapter = HistoryAdapter(dataModel.data)
            show()
        }
        hideLoading()
    }
}