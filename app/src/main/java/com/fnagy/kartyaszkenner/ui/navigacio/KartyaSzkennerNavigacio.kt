package com.fnagy.kartyaszkenner.ui.navigacio

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fnagy.kartyaszkenner.R
import com.fnagy.kartyaszkenner.ui.szkenneles.SzkennelesiKepernyő
import com.fnagy.kartyaszkenner.ui.gyujtemeny.GyujtemenyKepernyő
import com.fnagy.kartyaszkenner.ui.beallitasok.BeallitasokKepernyő

/**
 * Fő navigációs komponens
 */
@Composable
fun KartyaSzkennerNavigacio(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                NavigaciosElemek.items.forEach { elem ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = elem.ikon),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(stringResource(elem.cim))
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == elem.utvonal } == true,
                        onClick = {
                            navController.navigate(elem.utvonal) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigaciosElemek.Szkenneles.utvonal,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigaciosElemek.Szkenneles.utvonal) {
                SzkennelesiKepernyő()
            }
            composable(NavigaciosElemek.Gyujtemeny.utvonal) {
                GyujtemenyKepernyő()
            }
            composable(NavigaciosElemek.Beallitasok.utvonal) {
                BeallitasokKepernyő()
            }
        }
    }
}