package com.phuy.superff.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phuy.superff.modules.ShellActions
import kotlinx.coroutines.launch

@Composable
fun MemoryScreen() {
    val s = rememberCoroutineScope()
    var out by remember { mutableStateOf("") }
    var pkg by remember { mutableStateOf("") }
    var show1 by remember { mutableStateOf(false) }
    var show2 by remember { mutableStateOf(false) }

    LazyColumn(modifier = Modifier.padding(12.dp)) {
        item {
            Column {
                Button(onClick = { s.launch { out = ShellActions.killAll().out } }) { Text("am kill-all") }
                Button(onClick = { s.launch { out = ShellActions.trim().out } }) { Text("trim-caches 1G") }
                Button(onClick = { s.launch { out = ShellActions.meminfo().out.take(3000) } }) { Text("dumpsys meminfo") }
                Button(onClick = { s.launch { out = ShellActions.ps().out.take(3000) } }) { Text("ps -A") }
                Button(onClick = { s.launch { out = ShellActions.screencap().out } }) { Text("screencap") }
                OutlinedTextField(pkg, { pkg = it }, label = { Text("package (force-stop / pm clear)") })
                Row {
                    Button(onClick = { s.launch { out = ShellActions.forceStop(pkg).out } }) { Text("Force-stop") }
                    Button(onClick = { show1 = true }) { Text("pm clear") }
                }
                Text(out)
            }
        }
    }

    if (show1) AlertDialog(
        onDismissRequest = { show1 = false },
        confirmButton = { TextButton(onClick = { show1 = false; show2 = true }) { Text("Tiep tuc") } },
        dismissButton = { TextButton(onClick = { show1 = false }) { Text("Huy") } },
        text = { Text("Ban co chac muon xoa data app $pkg? Khong the hoan tac!") }
    )
    if (show2) AlertDialog(
        onDismissRequest = { show2 = false },
        confirmButton = {
            TextButton(onClick = {
                show2 = false
                s.launch { out = ShellActions.pmClear(pkg).out }
            }) { Text("XOA NGAY") }
        },
        dismissButton = { TextButton(onClick = { show2 = false }) { Text("Huy") } },
        text = { Text("Xac nhan lan 2: xoa toan bo data $pkg?") }
    )
}
