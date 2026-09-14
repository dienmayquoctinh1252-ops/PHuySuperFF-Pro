package com.phuy.superff

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phuy.superff.core.shizuku.ShizukuManager
import com.phuy.superff.ui.navigation.Nav
import com.phuy.superff.ui.screens.DisplayScreen
import com.phuy.superff.ui.screens.GroupsScreen
import com.phuy.superff.ui.screens.HomeScreen
import com.phuy.superff.ui.screens.MemoryScreen
import com.phuy.superff.ui.screens.MiscScreen
import com.phuy.superff.ui.screens.NetworkScreen
import com.phuy.superff.ui.screens.SettingsScreen
import com.phuy.superff.ui.screens.StatsScreen
import com.phuy.superff.ui.screens.ThermalScreen
import com.phuy.superff.ui.screens.TouchScreen
import com.phuy.superff.ui.theme.NeonTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var shizuku: ShizukuManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeonTheme {
                val vm: MainViewModel = hiltViewModel()
                val nav = rememberNavController()
                val conn by shizuku.connected.collectAsState()
                val vals by vm.vals.collectAsState()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("PHuySuperFF Pro") },
                            actions = { Badge { Text(if (conn) "SHIZUKU OK" else "NO") } }
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(false, { nav.navigate(Nav.HOME) }, { Text("Home") }, {})
                            NavigationBarItem(false, { nav.navigate(Nav.GROUPS) }, { Text("Groups") }, {})
                            NavigationBarItem(false, { nav.navigate(Nav.STATS) }, { Text("Stats") }, {})
                            NavigationBarItem(false, { nav.navigate(Nav.SETTINGS) }, { Text("Settings") }, {})
                        }
                    }
                ) { pad ->
                    NavHost(nav, Nav.HOME, Modifier.padding(pad)) {
                        composable(Nav.HOME) {
                            HomeScreen(conn, vals.count { it.value != "0" && it.value != "null" }) {
                                nav.navigate(it)
                            }
                        }
                        composable(Nav.GROUPS) { GroupsScreen { nav.navigate(it) } }
                        composable(Nav.STATS) { StatsScreen(vm) }
                        composable(Nav.SETTINGS) { SettingsScreen(vm, conn) }
                        composable(Nav.TOUCH) { TouchScreen(vm) }
                        composable(Nav.DISPLAY) { DisplayScreen(vm) }
                        composable(Nav.NETWORK) { NetworkScreen(vm) }
                        composable(Nav.THERMAL) { ThermalScreen(vm) }
                        composable(Nav.MEMORY) { MemoryScreen() }
                        composable(Nav.MISC) { MiscScreen(vm) }
                        // alias tu GroupsScreen
                        composable("touch") { TouchScreen(vm) }
                        composable("display") { DisplayScreen(vm) }
                        composable("network") { NetworkScreen(vm) }
                        composable("thermal") { ThermalScreen(vm) }
                        composable("memory") { MemoryScreen() }
                        composable("misc") { MiscScreen(vm) }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        shizuku.onStart()
        shizuku.requestPerm()
        if (!shizuku.hasPerm()) {
            Toast.makeText(this, "Bat Shizuku roi cap quyen", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        shizuku.onStop()
        super.onPause()
    }
}
