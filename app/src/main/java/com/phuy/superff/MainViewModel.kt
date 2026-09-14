package com.phuy.superff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phuy.superff.core.shizuku.AdbShell
import com.phuy.superff.data.LogRepository
import com.phuy.superff.data.ProfileRepo
import com.phuy.superff.modules.SystemTweaks
import com.phuy.superff.modules.TweakDef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val profile: ProfileRepo,
    val logs: LogRepository
) : ViewModel() {

    private val _vals = MutableStateFlow<Map<String, String>>(emptyMap())
    val vals: StateFlow<Map<String, String>> = _vals
    val logFlow = logs.flow()

    fun apply(d: TweakDef, v: String, onToast: (String) -> Unit) {
        viewModelScope.launch {
            val old = _vals.value[d.id] ?: d.def
            val put = AdbShell.settingsPut(d.ns, d.key, v)
            val get = AdbShell.settingsGet(d.ns, d.key)
            val got = get.out.trim()
            val ok = put.code == 0 && (got == v || got.equals(v, true))
            if (!ok) {
                onToast("Key ${d.key} không được hỗ trợ trên máy bạn (get=$got)")
                logs.add(d.id, old, v, "failed:get=$got")
            } else {
                _vals.value = _vals.value + (d.id to v)
                profile.save(_vals.value)
                logs.add(d.id, old, v, "success")
            }
        }
    }

    fun restoreAll(onToast: (String) -> Unit) {
        viewModelScope.launch {
            // CHI AllSafe, khong bao gom pm clear / shell
            SystemTweaks.AllSafe.forEach { d ->
                val r = AdbShell.settingsPut(d.ns, d.key, d.def)
                logs.add(d.id, _vals.value[d.id] ?: "?", d.def, if (r.code == 0) "restored" else "fail")
            }
            _vals.value = SystemTweaks.AllSafe.associate { it.id to it.def }
            profile.save(_vals.value)
            onToast("Đã Restore All về mặc định")
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            _vals.value = profile.load()
        }
    }
}
