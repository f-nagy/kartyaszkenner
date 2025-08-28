package com.fnagy.kartyaszkenner.ui.gyujtemeny

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.fnagy.kartyaszkenner.funkciok.gyujtemeny.GyujtemenyViewModel
import com.fnagy.kartyaszkenner.funkciok.gyujtemeny.ExportAllapot

/**
 * Gyűjtemény képernyő
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GyujtemenyKepernyő(
    viewModel: GyujtemenyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val kartyak by viewModel.kartyak.collectAsStateWithLifecycle()
    val keresoSzoveg by viewModel.keresoSzoveg.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.gyujtemeny_cim),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Statisztikák kártya
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                uiState.statisztikak?.let { stats ->
                    Text(
                        text = stringResource(R.string.osszesen_kartya, stats.osszesKartyaDarab),
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "${stats.kartyakSzama} egyedi kártya",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                } ?: run {
                    Text(
                        text = stringResource(R.string.osszesen_kartya, 0),
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = stringResource(R.string.gyujtemeny_ures),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Keresés
        OutlinedTextField(
            value = keresoSzoveg,
            onValueChange = viewModel::keresoSzovegFrissites,
            label = { Text("Keresés") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Export gombok
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { viewModel.csvExport() },
                modifier = Modifier.weight(1f),
                enabled = uiState.exportAllapot != ExportAllapot.FOLYAMATBAN
            ) {
                if (uiState.exportAllapot == ExportAllapot.FOLYAMATBAN) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                } else {
                    Text(stringResource(R.string.export_csv))
                }
            }
            
            OutlinedButton(
                onClick = { viewModel.jsonExport() },
                modifier = Modifier.weight(1f),
                enabled = uiState.exportAllapot != ExportAllapot.FOLYAMATBAN
            ) {
                if (uiState.exportAllapot == ExportAllapot.FOLYAMATBAN) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                } else {
                    Text(stringResource(R.string.export_json))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Kártyák listája
        if (kartyak.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (keresoSzoveg.isNotBlank()) {
                            "Nincs találat"
                        } else {
                            stringResource(R.string.gyujtemeny_ures)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(kartyak) { kartya ->
                    KartyaListaElem(
                        kartya = kartya,
                        onDarabszamValtoztatas = { ujDarabszam ->
                            viewModel.darabszamModositas(kartya, ujDarabszam)
                        },
                        onTorles = {
                            viewModel.kartyaTorles(kartya)
                        }
                    )
                }
            }
        }
        
        // Hibaüzenetek és sikerüzenetek
        uiState.hibaUzenet?.let { hiba ->
            Spacer(modifier = Modifier.height(8.dp))
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
        
        uiState.sikeresUzenet?.let { uzenet ->
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = uzenet,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
    
    // Üzenetek automatikus törlése
    LaunchedEffect(uiState.hibaUzenet, uiState.sikeresUzenet) {
        if (uiState.hibaUzenet != null || uiState.sikeresUzenet != null) {
            kotlinx.coroutines.delay(3000)
            viewModel.uzenetekTorlese()
        }
    }
}

@Composable
private fun KartyaListaElem(
    kartya: com.fnagy.kartyaszkenner.adatok.modellek.Kartya,
    onDarabszamValtoztatas: (Int) -> Unit,
    onTorles: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = kartya.nev,
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = kartya.tipus,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.kartya_darab, kartya.darabszam),
                    style = MaterialTheme.typography.bodySmall
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onDarabszamValtoztatas(kartya.darabszam - 1) },
                        enabled = kartya.darabszam > 1
                    ) {
                        Text("-")
                    }
                    
                    Text(
                        text = kartya.darabszam.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    IconButton(
                        onClick = { onDarabszamValtoztatas(kartya.darabszam + 1) }
                    ) {
                        Text("+")
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    TextButton(
                        onClick = onTorles
                    ) {
                        Text(stringResource(R.string.torles))
                    }
                }
            }
        }
    }
}