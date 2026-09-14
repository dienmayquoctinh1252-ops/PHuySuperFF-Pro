package com.phuy.superff.modules

data class TweakDef(
    val id: String,
    val ns: String, // system | secure | global
    val key: String,
    val def: String
)

object SystemTweaks {
    val Touch = listOf(
        TweakDef("pointer_speed", "system", "pointer_speed", "1"),
        TweakDef("touch_sampling_rate", "secure", "touch_sampling_rate", "120"),
        TweakDef("multi_touch_enabled", "global", "multi_touch_enabled", "1"),
        TweakDef("touch_exploration_enabled", "secure", "touch_exploration_enabled", "0"),
        TweakDef("haptic_feedback_enabled", "system", "haptic_feedback_enabled", "1"),
        TweakDef("sound_effects_enabled", "system", "sound_effects_enabled", "1"),
        TweakDef("vibrate_on", "system", "vibrate_on", "1")
    )

    val Display = listOf(
        TweakDef("peak_refresh_rate", "system", "peak_refresh_rate", "120"),
        TweakDef("min_refresh_rate", "system", "min_refresh_rate", "60"),
        TweakDef("screen_brightness_mode", "system", "screen_brightness_mode", "0"),
        TweakDef("screen_brightness", "system", "screen_brightness", "128"),
        TweakDef("accelerometer_rotation", "system", "accelerometer_rotation", "1"),
        TweakDef("user_rotation", "system", "user_rotation", "0"),
        TweakDef("stay_on_while_plugged_in", "global", "stay_on_while_plugged_in", "0"),
        TweakDef("animator_duration_scale", "global", "animator_duration_scale", "1"),
        TweakDef("transition_animation_scale", "global", "transition_animation_scale", "1"),
        TweakDef("window_animation_scale", "global", "window_animation_scale", "1")
    )

    val Network = listOf(
        TweakDef("private_dns_mode", "global", "private_dns_mode", "hostname"),
        TweakDef("private_dns_specifier", "global", "private_dns_specifier", "dns.google"),
        TweakDef("wifi_scan_always_enabled", "global", "wifi_scan_always_enabled", "0"),
        TweakDef("mobile_data_always_on", "global", "mobile_data_always_on", "0"),
        TweakDef("wifi_sleep_policy", "global", "wifi_sleep_policy", "2"),
        TweakDef("captive_portal_detection_enabled", "global", "captive_portal_detection_enabled", "1"),
        TweakDef("bluetooth_on", "global", "bluetooth_on", "1"),
        TweakDef("data_roaming", "global", "data_roaming", "0")
    )

    val Thermal = listOf(
        TweakDef("low_power", "global", "low_power", "0")
    )

    val Misc = listOf(
        TweakDef("development_settings_enabled", "global", "development_settings_enabled", "0"),
        TweakDef("auto_time", "global", "auto_time", "1"),
        TweakDef("auto_time_zone", "global", "auto_time_zone", "1"),
        TweakDef("policy_control", "global", "policy_control", "null")
    )

    val AllSafe: List<TweakDef> = Touch + Display + Network + Thermal + Misc
}
