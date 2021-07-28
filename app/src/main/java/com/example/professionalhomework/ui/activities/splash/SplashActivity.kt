package com.example.professionalhomework.ui.activities.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import com.example.professionalhomework.databinding.ActivitySplashScreenBinding
import com.example.professionalhomework.ui.activities.main.MainActivity
import com.example.professionalhomework.ui.base.di.BaseDaggerActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class SplashActivity : BaseDaggerActivity(), SplashView {

    @Inject
    lateinit var splashPresenterFactory: SplashPresenterFactory

    private lateinit var binding: ActivitySplashScreenBinding

    private val splashPresenter by moxyPresenter {
        splashPresenterFactory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivitySplashScreenBinding.inflate(layoutInflater).also { setContentView(it.root) }
        splashPresenter.onCreate()
    }

    override fun setupAnimation() {
        binding.lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = Unit

            override fun onAnimationEnd(animation: Animator?) = Unit

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = splashPresenter.onAnimationEnd()
        })
    }

    override fun startMainActivity() {
        binding.lottieAnimationView.pauseAnimation()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}