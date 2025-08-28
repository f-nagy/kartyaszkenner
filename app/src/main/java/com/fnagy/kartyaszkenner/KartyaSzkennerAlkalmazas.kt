package com.fnagy.kartyaszkenner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * KártyaSzkenner alkalmazás fő osztálya
 * Hilt dependency injection inicializálása
 */
@HiltAndroidApp
class KartyaSzkennerAlkalmazas : Application() {
    
    override fun onCreate() {
        super.onCreate()
    }
}