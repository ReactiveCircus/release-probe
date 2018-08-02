package ychescale9.infra.mapper

import java.util.Collections.emptyList
import org.amshove.kluent.shouldEqual
import org.junit.Test

class BaseDataMapperTest {

    val mapper = TestDataMapper()

    @Test
    fun `should return empty list for empty collection`() {
        mapper.transform(emptyList()) shouldEqual emptyList<Any>()
    }

    @Test
    fun `should return list of transformed items for each item`() {
        val froms = listOf(1, 2, 3)
        mapper.transform(froms) shouldEqual listOf("1", "2", "3")
    }
}

class TestDataMapper : BaseDataMapper<Int, String>() {
    override fun transform(model: Int, vararg params: Any): String {
        return model.toString()
    }
}
