package com.example.professionalhomework.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.professionalhomework.R
import com.example.professionalhomework.databinding.ActivityMainBinding
import com.example.professionalhomework.utils.Extensions.updateAvailable
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var appUpdateManager: AppUpdateManager

    private val stateUpdateListener = InstallStateUpdatedListener { state ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) popupSnackBarForCompleteUpdate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        checkForUpdates()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()

    private fun checkForUpdates() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfo = appUpdateManager.appUpdateInfo

        appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailable() && it.isUpdateTypeAllowed(IMMEDIATE)) {
                appUpdateManager.registerListener(stateUpdateListener)
                appUpdateManager.startUpdateFlowForResult(
                    it,
                    IMMEDIATE,
                    this,
                    REQUEST_CODE
                )
            }
        }
    }

    private fun popupSnackBarForCompleteUpdate() {
        Snackbar.make(
            binding.root,
            "DownloadingText",
            Snackbar.LENGTH_SHORT
        )
            .apply { setAction("Restart") { appUpdateManager.completeUpdate() } }
            .show()
    }
}
