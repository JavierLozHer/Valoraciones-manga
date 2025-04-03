package edu.iesam.valoracionesmanga.features.assessment.data.local.converters

import androidx.room.TypeConverter

class AssessmentConverters {

    @TypeConverter
    fun numberToString(value: Number): String {
        return value.toString()
    }

    @TypeConverter
    fun numberFromString(value: String): Number {
        return value.toInt()
    }

}