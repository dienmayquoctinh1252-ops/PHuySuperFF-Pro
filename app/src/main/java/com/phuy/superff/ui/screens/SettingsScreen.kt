package com.phuy.superff.ui.screens

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.phuy.superff.MainViewModel
import com.phuy.superff.core.shizuku.AdbShell
import com.phuy.superff.modules.SystemTweaks
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(vm: MainViewModel, connected: Boolean) {
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()
    var msg by remember { mutableStateOf("") }

    val picker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            s.launch {
                val txt = (ctx as Activity).contentResolver.openInputStream(uri)?.bufferedReader()?.readText() ?: ""
                vm.profile.importJson(txt)
                val m = vm.profile.load()
                SystemTweaks.AllSafe.forEach { d ->
                    m[d.id]?.let { v -> AdbShell.settingsPut(d.ns, d.key, v) }
                }
                msg = "Imported ${m.size} keys"
            }
        }
    }

    Column(modifier = Modifier.padding(12.dp)) {
        Text(if (connected) "SHIZUKU Connected" else "SHIZUKU Disconnected - mo app Shizuku de bat")
        Button(onClick = {
            try {
                val i = ctx.packageManager.getLaunchIntentForPackage("moe.shizuku.privileged.api")
                    ?: Intent()
                ctx.startActivity(i)
            } catch (_: Exception) {}
        }) { Text("Mo Shizuku") }

        Button(onClick = {
            s.launch {
                val json = vm.profile.exportJson()
                val cv = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, "phuy_superff_profile.json")
                    put(MediaStore.Downloads.MIME_TYPE, "application/json")
                    put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }
                val uri = ctx.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, cv)
                uri?.let { ctx.contentResolver.openOutputStream(it)?.write(json.toByteArray()) }
                msg = "Exported to Download/phuy_superff_profile.json"
            }
        }) { Text("Export JSON") }

        Button(onClick = { picker.launch(arrayOf("application/json")) }) { Text("Import JSON") }

        Button(onClick = { s.launch { vm.restoreAll { msg = it } } }) { Text("Restore All") }

        Text(msg)
    }
}
