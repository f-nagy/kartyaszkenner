package com.fnagy.kartyaszkenner.ui.navigacio

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fnagy.kartyaszkenner.R

/**
 * Navigációs elemek definíciója
 */
sealed class NavigaciosElemek(
    val utvonal: String,
    @StringRes val cim: Int,
    @DrawableRes val ikon: Int
) {
    object Szkenneles : NavigaciosElemek(
        utvonal = "szkenneles",
        cim = R.string.nav_szkenneles,
        ikon = R.drawable.ic_szkenneles
    )
    
    object Gyujtemeny : NavigaciosElemek(
        utvonal = "gyujtemeny", 
        cim = R.string.nav_gyujtemeny,
        ikon = R.drawable.ic_gyujtemeny
    )
    
    object Beallitasok : NavigaciosElemek(
        utvonal = "beallitasok",
        cim = R.string.nav_beallitasok,
        ikon = R.drawable.ic_beallitasok
    )
    
    companion object {
        val items = listOf(Szkenneles, Gyujtemeny, Beallitasok)
    }
}