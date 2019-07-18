package reactivecircus.releaseprobe.persistence

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromCommaSeparatedString(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun listOfStringsToCommaSeparatedString(values: List<String>): String {
        return values.joinToString(",")
    }
}
