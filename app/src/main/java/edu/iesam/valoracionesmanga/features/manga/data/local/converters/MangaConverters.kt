package edu.iesam.valoracionesmanga.features.manga.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.iesam.valoracionesmanga.features.manga.domain.Staff

class MangaConverters {

    @TypeConverter
    fun genresFromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun genresToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun staffFromString(value: String): List<Staff> {
        val listType = object : TypeToken<List<Staff>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun staffToString(list: List<Staff>): String {
        return Gson().toJson(list)
    }

}