package cz.vostinak.nba.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cz.vostinak.nba.ui.navigation.MainNavHost
import cz.vostinak.core.ui.theme.NBATheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


/**
 * Single app activity
 */
@AndroidEntryPoint
class SingleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NBATheme {
                MainNavHost()
            }
        }
    }
}