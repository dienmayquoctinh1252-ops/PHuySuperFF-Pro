package com.phuy.superff.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.phuy.superff.MainViewModel

@Composable
fun StatsScreen(vm: MainViewModel) {
    val l by vm.logFlow.collectAsState(emptyList())
    LazyColumn {
        items(l) { e ->
            Text("${e.ts} ${e.tweakId} ${e.old}->${e.new} ${e.result}")
        }
    }
}
