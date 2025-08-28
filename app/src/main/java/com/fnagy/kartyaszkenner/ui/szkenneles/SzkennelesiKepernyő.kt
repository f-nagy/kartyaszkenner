package com.fnagy.kartyaszkenner.ui.szkenneles

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fnagy.kartyaszkenner.R
import com.fnagy.kartyaszkenner.funkciok.szkenneles.SzkennelesiViewModel

/**
 * Szkennelési képernyő
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SzkennelesiKepernyő(
    viewModel: SzkennelesiViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val ideiglenesKartyak by viewModel.ideiglenesKartyak.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.szkenneles_cim),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Kamera placeholder
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (uiState.kameraEngedelyezve) {
                        "Kamera nézet aktív"
                    } else {
                        stringResource(R.string.kamera_engedelykeres)
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Felismerés állapot
        Text(
            text = when {
                uiState.aktualisFelismertKartya != null -> stringResource(R.string.kartya_felismert)
                uiState.felismeresAllapot.name == "BIZONYTALAN" -> stringResource(R.string.bizonytalan_felismeres)
                else -> stringResource(R.string.felismeres_folyamatban)
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        // Felismert kártya megjelenítése
        uiState.aktualisFelismertKartya?.let { felismertKartya ->
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = felismertKartya.kartya?.nev ?: "Ismeretlen kártya",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Típus: ${felismertKartya.kartya?.tipus ?: "N/A"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Text(
                        text = "Bizalom: ${(felismertKartya.felismeresiBizalom * 100).toInt()}%",
                        style = MaterialTheme.typography.bodySmall
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Button(
                        onClick = { viewModel.kartyaHozzaadasaGyujtemenybe(felismertKartya) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.hozzaadas_gyujtemenybe))
                    }
                }
            }
        }
        
        // Ideiglenes lista információ
        if (ideiglenesKartyak.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "${ideiglenesKartyak.size} kártya szkennelve",
                        style = MaterialTheme.typography.titleSmall
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.osszesIdeiglenesKartyaHozzaadas() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(stringResource(R.string.osszes_hozzaadas))
                        }
                        
                        OutlinedButton(
                            onClick = { viewModel.ideiglenesListaTorlese() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(stringResource(R.string.lista_torlese))
                        }
                    }
                }
            }
        }
        
        // Hibakezelés
        uiState.hibaUzenet?.let { hiba ->
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = hiba,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
    
    // Siker üzenet kezelése
    LaunchedEffect(uiState.sikeresHozzaadas) {
        if (uiState.sikeresHozzaadas) {
            viewModel.uiUzenetekVissaallitasa()
        }
    }
}