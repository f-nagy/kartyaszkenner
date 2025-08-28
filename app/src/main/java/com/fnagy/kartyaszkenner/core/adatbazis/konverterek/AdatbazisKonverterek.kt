package com.fnagy.kartyaszkenner.core.adatbazis.konverterek

import androidx.room.TypeConverter
import com.fnagy.kartyaszkenner.adatok.modellek.FelismeresiAllapot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Room adatbázis type converter-ek
 */
class AdatbazisKonverterek {
    
    private val gson = Gson()
    
    @TypeConverter
    fun fromFelismeresiAllapot(allapot: FelismeresiAllapot): String {
        return allapot.name
    }
    
    @TypeConverter
    fun toFelismeresiAllapot(allapotString: String): FelismeresiAllapot {
        return FelismeresiAllapot.valueOf(allapotString)
    }
    
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return if (list == null) null else gson.toJson(list)
    }
    
    @TypeConverter
    fun toStringList(jsonString: String?): List<String>? {
        return if (jsonString == null) {
            null
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(jsonString, listType)
        }
    }
    
    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return if (list == null) null else gson.toJson(list)
    }
    
    @TypeConverter
    fun toIntList(jsonString: String?): List<Int>? {
        return if (jsonString == null) {
            null
        } else {
            val listType = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(jsonString, listType)
        }
    }
}