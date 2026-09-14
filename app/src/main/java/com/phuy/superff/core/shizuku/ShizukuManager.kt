package com.phuy.superff.core.shizuku

import android.content.Context
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import rikka.shizuku.Shizuku
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShizukuManager @Inject constructor(
    @ApplicationContext private val ctx: Context
) {
    private val _connected = MutableStateFlow(false)
    val connected: StateFlow<Boolean> = _connected

    private val binderListener = Shizuku.OnBinderReceivedListener {
        _connected.value = try { Shizuku.pingBinder() } catch (_: Exception) { false }
    }
    private val permListener = Shizuku.OnRequestPermissionResultListener { _, grantResult ->
        _connected.value = grantResult == PackageManager.PERMISSION_GRANTED &&
                try { Shizuku.pingBinder() } catch (_: Exception) { false }
    }

    fun onStart() {
        Shizuku.addBinderReceivedListenerSticky(binderListener)
        Shizuku.addRequestPermissionResultListener(permListener)
        _connected.value = try { Shizuku.pingBinder() } catch (_: Exception) { false }
    }

    fun onStop() {
        try { Shizuku.removeBinderReceivedListener(binderListener) } catch (_: Exception) {}
        try { Shizuku.removeRequestPermissionResultListener(permListener) } catch (_: Exception) {}
    }

    fun isAvailable(): Boolean = try { Shizuku.pingBinder() } catch (_: Exception) { false }

    fun hasPerm(): Boolean = try {
        Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    } catch (_: Exception) { false }

    fun requestPerm() {
        if (isAvailable() && !hasPerm()) {
            Shizuku.requestPermission(1001)
        }
    }
}
