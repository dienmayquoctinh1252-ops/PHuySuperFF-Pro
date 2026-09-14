package com.phuy.superff.core.shizuku

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader

data class ShellResult(val code: Int, val out: String)

object AdbShell {
    suspend fun exec(vararg cmd: String): ShellResult = withContext(Dispatchers.IO) {
        val alive = try { Shizuku.pingBinder() } catch (_: Exception) { false }
        if (!alive) return@withContext ShellResult(-1, "Shizuku not running")
        try {
            val p = Shizuku.newProcess(cmd, null, null)
            val o = BufferedReader(InputStreamReader(p.inputStream)).readText() +
                    BufferedReader(InputStreamReader(p.errorStream)).readText()
            val c = p.waitFor()
            p.destroy()
            ShellResult(c, o.trim())
        } catch (e: Exception) {
            ShellResult(-1, e.message ?: "exec failed")
        }
    }

    suspend fun settingsPut(ns: String, key: String, value: String): ShellResult {
        require(ns in setOf("system", "secure", "global")) { "bad namespace" }
        return exec("settings", "put", ns, key, value)
    }

    suspend fun settingsGet(ns: String, key: String): ShellResult {
        require(ns in setOf("system", "secure", "global")) { "bad namespace" }
        return exec("settings", "get", ns, key)
    }
}
