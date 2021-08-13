package com.example.professionalhomework.presentation.base.view

import androidx.fragment.app.Fragment
import com.example.professionalhomework.presentation.entities.AppState

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract fun renderData(dataModel: T)

    abstract fun showLoading()

    abstract fun hideLoading()

    abstract fun showError()

}