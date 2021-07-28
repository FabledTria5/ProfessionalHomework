package com.example.professionalhomework.ui.adapters.rv.word

interface WordsListPresenter<V> {
    fun bindView(view: V, position: Int)
    fun getCount(): Int
}