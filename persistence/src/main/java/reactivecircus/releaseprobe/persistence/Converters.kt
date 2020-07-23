package reactivecircus.releaseprobe.persistence

import androidx.room.TypeConverter

object Converters {

    @TypeConverter
    fun commaSeparatedStringToListOfStrings(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun listOfStringsToCommaSeparatedString(values: List<String>): String {
        return values.joinToString(",")
    }
}
