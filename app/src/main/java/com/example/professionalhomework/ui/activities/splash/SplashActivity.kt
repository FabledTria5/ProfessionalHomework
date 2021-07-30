package com.example.professionalhomework.ui.activities.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.professionalhomework.databinding.ActivitySplashScreenBinding
import com.example.professionalhomework.ui.activities.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivitySplashScreenBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setupAnimation()
    }

    private fun setupAnimation() {
        binding.lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = Unit

            override fun onAnimationEnd(animation: Animator?) = Unit

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = startMainActivity()
        })
    }

    fun startMainActivity() {
        binding.lottieAnimationView.pauseAnimation()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}