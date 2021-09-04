package com.example.professionalhomework.presentation.base.view

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.professionalhomework.presentation.entities.AppState

abstract class BaseFragment<T : AppState>(@LayoutRes layout: Int) : Fragment(layout) {

    abstract fun renderData(dataModel: T)

    abstract fun setupUi()

    abstract fun showLoading()

    abstract fun hideLoading()

    abstract fun showError()

}