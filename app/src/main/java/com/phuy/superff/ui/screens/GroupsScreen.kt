package com.phuy.superff.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phuy.superff.ui.components.NeonCard

@Composable
fun GroupsScreen(onOpen: (String) -> Unit) {
    Column(modifier = Modifier.padding(12.dp)) {
        listOf("touch", "display", "network", "thermal", "memory", "misc").forEach {
            NeonCard(it, it, { onOpen(it) })
        }
    }
}
