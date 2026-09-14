package com.phuy.superff.modules

import com.phuy.superff.core.shizuku.AdbShell

object ShellActions {
    suspend fun killAll() = AdbShell.exec("am", "kill-all")
    suspend fun forceStop(pkg: String) = AdbShell.exec("am", "force-stop", pkg.trim())
    suspend fun trim() = AdbShell.exec("cmd", "package", "trim-caches", "1G")
    suspend fun meminfo() = AdbShell.exec("dumpsys", "meminfo", "-s")
    suspend fun ps() = AdbShell.exec("ps", "-A")
    suspend fun screencap() = AdbShell.exec("screencap", "/sdcard/screen.png")
    suspend fun pmClear(pkg: String) = AdbShell.exec("pm", "clear", pkg.trim())
}
