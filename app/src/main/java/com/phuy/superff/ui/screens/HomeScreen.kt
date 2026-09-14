package com.phuy.superff.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phuy.superff.ui.components.NeonCard

@Composable
fun HomeScreen(connected: Boolean, onCount: Int, onOpen: (String) -> Unit) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            if (connected) "SHIZUKU Connected" else "SHIZUKU Disconnected - bat app Shizuku truoc",
            color = MaterialTheme.colorScheme.primary
        )
        Text("Dang bat: $onCount tweak")
        listOf(
            "touch" to "Touch",
            "display" to "Display",
            "network" to "Network",
            "thermal" to "Thermal",
            "memory" to "Memory",
            "misc" to "Misc"
        ).forEach {
            NeonCard(it.second, "mo >", { onOpen(it.first) })
        }
    }
}
