package com.example.professionalhomework.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.professionalhomework.R
import com.example.professionalhomework.databinding.ActivityMainBinding
import com.example.professionalhomework.utils.ConnectivityLiveData
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
    private lateinit var connectivityLiveData: ConnectivityLiveData

    private val stateUpdateListener = InstallStateUpdatedListener { state ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) popupSnackBarForCompleteUpdate()
    }

    private val connectionSnackBar by lazy {
        Snackbar.make(
            binding.root,
            "No internet connection",
            Snackbar.LENGTH_INDEFINITE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        checkForUpdates()
        setupConnectionListener()
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

    private fun setupConnectionListener() {
        connectivityLiveData = ConnectivityLiveData(application = application)
        connectivityLiveData.observe(this) { isAvailable ->
            when (isAvailable) {
                true -> connectionSnackBar.dismiss()
                false -> connectionSnackBar.show()
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
