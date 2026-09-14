package com.phuy.superff.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phuy.superff.ui.theme.Purple

@Composable
fun NeonCard(title: String, count: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(6.dp)
            .border(1.dp, Purple, RoundedCornerShape(12.dp)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title)
            Text(count)
        }
    }
}
