package ychescale9.infra.extension

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.Test

class StandardExtensionsTest {

    @Test
    fun `notNullOrThrow should return value when value is not null`() {
        val value: String? = "string"
        value.notNullOrThrow() shouldEqual "string"
    }

    @Test
    fun `notNullOrThrow should throw IllegalArgumentException when value is null`() {
        val value: String? = null
        invoking { value.notNullOrThrow() } shouldThrow IllegalArgumentException::class
    }
}
