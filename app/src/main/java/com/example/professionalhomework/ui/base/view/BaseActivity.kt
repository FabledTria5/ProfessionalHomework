package com.example.professionalhomework.ui.base.view

import androidx.appcompat.app.AppCompatActivity
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.ui.base.viewmodel.BaseViewModel
import com.example.professionalhomework.ui.interactor.Interactor

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(dataModel: T)

}