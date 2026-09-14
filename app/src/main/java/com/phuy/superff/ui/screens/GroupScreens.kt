package com.phuy.superff.ui.screens

import androidx.compose.runtime.Composable
import com.phuy.superff.MainViewModel
import com.phuy.superff.modules.SystemTweaks

@Composable fun TouchScreen(vm: MainViewModel) = GroupList(SystemTweaks.Touch, vm)
@Composable fun DisplayScreen(vm: MainViewModel) = GroupList(SystemTweaks.Display, vm)
@Composable fun NetworkScreen(vm: MainViewModel) = GroupList(SystemTweaks.Network, vm)
@Composable fun ThermalScreen(vm: MainViewModel) = GroupList(SystemTweaks.Thermal, vm)
@Composable fun MiscScreen(vm: MainViewModel) = GroupList(SystemTweaks.Misc, vm)
