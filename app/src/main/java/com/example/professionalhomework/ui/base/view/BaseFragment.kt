package com.example.professionalhomework.ui.base.view

import androidx.fragment.app.Fragment
import com.example.professionalhomework.data.model.AppState

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract fun renderData(dataModel: T)

}