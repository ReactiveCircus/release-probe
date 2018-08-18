package ychescale9.releaseprobe.persistence

import org.amshove.kluent.shouldEqual
import org.junit.Test

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun `fromCommaSeparatedString should convert valid comma separated string to list of strings`() {
        val commaSeparatedString = "1.0.0-alpha1, 1.0.0-alpha2, 1.0.0-alpha3, 1.1.0"
        val result = converters.fromCommaSeparatedString(commaSeparatedString)
        result shouldEqual listOf("1.0.0-alpha1", "1.0.0-alpha2", "1.0.0-alpha3", "1.1.0")
    }

    @Test
    fun `fromCommaSeparatedString should convert string with no comma to a single-item list of strings`() {
        val commaSeparatedString = "1.0.0"
        val result = converters.fromCommaSeparatedString(commaSeparatedString)
        result shouldEqual listOf("1.0.0")
    }

    @Test
    fun `listOfStringsToCommaSeparatedString should convert list of strings to comma separated string`() {
        val listOfStrings = listOf("1.0.0-alpha1", "1.0.0-alpha2", "1.0.0-alpha3", "1.1.0")
        val result = converters.listOfStringsToCommaSeparatedString(listOfStrings)
        result shouldEqual "1.0.0-alpha1,1.0.0-alpha2,1.0.0-alpha3,1.1.0"
    }

    @Test
    fun `listOfStringsToCommaSeparatedString should convert single-item list of strings a string with no comma`() {
        val listOfStrings = listOf("1.0.0")
        val result = converters.listOfStringsToCommaSeparatedString(listOfStrings)
        result shouldEqual "1.0.0"
    }
}
