package com.fnagy.kartyaszkenner.ui.beallitasok

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.fnagy.kartyaszkenner.funkciok.beallitasok.BeallitasokViewModel
import com.fnagy.kartyaszkenner.adatok.modellek.*

/**
 * Beállítások képernyő
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeallitasokKepernyő(
    viewModel: BeallitasokViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val beallitasok by viewModel.beallitasok.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.beallitasok_cim),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Felismerési beállítások
        BeallitasSzekcio(
            cim = stringResource(R.string.felismeres_beallitasok)
        ) {
            Text(
                text = stringResource(R.string.felismeres_sebesseg),
                style = MaterialTheme.typography.titleSmall
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.gyors))
                Slider(
                    value = beallitasok.felismeresiSebesseg.ordinal.toFloat(),
                    onValueChange = { uj ->
                        val ujSebesség = FelismeresiSebesseg.values()[uj.toInt()]
                        viewModel.felismeresiSebessegValtoztatas(ujSebesség)
                    },
                    valueRange = 0f..2f,
                    steps = 1,
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
                )
                Text(stringResource(R.string.pontos))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Hang beállítások
        BeallitasSzekcio(
            cim = stringResource(R.string.hang_beallitasok)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.hangjelek_engedelyezese))
                Switch(
                    checked = beallitasok.hangjelzesekEngedelyezve,
                    onCheckedChange = viewModel::hangjelzesekKapcsolas
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.vibracio_engedelyezese))
                Switch(
                    checked = beallitasok.vibracioEngedelyezve,
                    onCheckedChange = viewModel::vibracioKapcsolas
                )
            }
            
            if (beallitasok.hangjelzesekEngedelyezve) {
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = stringResource(R.string.hangerősség),
                    style = MaterialTheme.typography.titleSmall
                )
                
                Slider(
                    value = beallitasok.hangerősség,
                    onValueChange = viewModel::hangerősségBeallitas,
                    valueRange = 0f..1f,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Adatkezelés beállítások
        BeallitasSzekcio(
            cim = stringResource(R.string.adatkezeles_beallitasok)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.csak_helyi_adatok))
                Switch(
                    checked = beallitasok.offlineMod,
                    onCheckedChange = viewModel::offlineModKapcsolas
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(R.string.duplikatum_kezeles),
                style = MaterialTheme.typography.titleSmall
            )
            
            Column {
                DuplikatumKezeles.values().forEach { kezeles ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = beallitasok.duplikatumKezeles == kezeles,
                            onClick = { viewModel.duplikatumKezelesBeallitas(kezeles) }
                        )
                        
                        Text(
                            text = when (kezeles) {
                                DuplikatumKezeles.ENGEDELYEZESE -> stringResource(R.string.duplikatum_engedelyezese)
                                DuplikatumKezeles.EGYESITES -> stringResource(R.string.duplikatum_egyesitese)
                                DuplikatumKezeles.TILTASA -> stringResource(R.string.duplikatum_tiltasa)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Megjelenés beállítások
        BeallitasSzekcio(
            cim = "Megjelenés"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Sötét téma")
                Switch(
                    checked = beallitasok.sotetTema,
                    onCheckedChange = viewModel::sotetTemaKapcsolas
                )
            }
        }
        
        // Hibaüzenetek és sikerüzenetek
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
        
        uiState.sikeresUzenet?.let { uzenet ->
            Spacer(modifier = Modifier.height(16.dp))
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
fun BeallitasSzekcio(
    cim: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = cim,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            content()
        }
    }
}