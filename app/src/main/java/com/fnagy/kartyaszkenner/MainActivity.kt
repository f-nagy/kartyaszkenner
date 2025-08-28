package com.fnagy.kartyaszkenner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.fnagy.kartyaszkenner.ui.navigacio.KartyaSzkennerNavigacio
import com.fnagy.kartyaszkenner.ui.tema.KartyaSzkennerTema
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fő activity - alkalmazás belépési pontja
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            KartyaSzkennerTema {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KartyaSzkennerNavigacio(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}