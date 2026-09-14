package com.phuy.superff.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phuy.superff.ui.theme.Cyan

@Composable
fun NeonButton(text: String, onClick: () -> Unit, mod: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = mod.border(1.dp, Cyan, RoundedCornerShape(12.dp))
    ) {
        Text(text)
    }
}
