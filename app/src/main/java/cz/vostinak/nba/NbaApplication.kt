package cz.vostinak.nba

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp


/**
 * NBA application class.
 */
@HiltAndroidApp
class NbaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}