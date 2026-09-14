package com.phuy.superff.ui.screens

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.phuy.superff.MainViewModel
import com.phuy.superff.modules.TweakDef
import com.phuy.superff.ui.components.SliderRow
import com.phuy.superff.ui.components.TweakRow

@Composable
fun GroupList(defs: List<TweakDef>, vm: MainViewModel) {
    val ctx = LocalContext.current
    val vals by vm.vals.collectAsState()
    LazyColumn {
        items(defs) { d ->
            val cur = vals[d.id] ?: d.def
            if (d.key == "screen_brightness" || d.key == "pointer_speed" || d.key == "stay_on_while_plugged_in") {
                val max = when (d.key) {
                    "screen_brightness" -> 255f
                    "pointer_speed" -> 7f
                    else -> 7f
                }
                SliderRow(d.id, cur.toFloatOrNull() ?: 0f, 0f..max) { v ->
                    vm.apply(d, v) { Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show() }
                }
            } else {
                TweakRow(d.id, cur) { v ->
                    vm.apply(d, v) { Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }
}
