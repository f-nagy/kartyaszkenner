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
import com.fnagy.kartyaszkenner.R

/**
 * Beállítások képernyő
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeallitasokKepernyő() {
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
            var felismeresiSebesseg by remember { mutableStateOf(1) }
            
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
                    value = felismeresiSebesseg.toFloat(),
                    onValueChange = { felismeresiSebesseg = it.toInt() },
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
            var hangjelzesekEngedelyezve by remember { mutableStateOf(true) }
            var vibracioEngedelyezve by remember { mutableStateOf(true) }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.hangjelek_engedelyezese))
                Switch(
                    checked = hangjelzesekEngedelyezve,
                    onCheckedChange = { hangjelzesekEngedelyezve = it }
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
                    checked = vibracioEngedelyezve,
                    onCheckedChange = { vibracioEngedelyezve = it }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Adatkezelés beállítások
        BeallitasSzekcio(
            cim = stringResource(R.string.adatkezeles_beallitasok)
        ) {
            var offlineMod by remember { mutableStateOf(false) }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.csak_helyi_adatok))
                Switch(
                    checked = offlineMod,
                    onCheckedChange = { offlineMod = it }
                )
            }
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