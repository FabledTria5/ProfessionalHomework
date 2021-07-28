package com.example.professionalhomework.rx

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}