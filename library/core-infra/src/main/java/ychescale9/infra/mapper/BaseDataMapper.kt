package ychescale9.infra.mapper

import java.util.Collections.emptyList

/**
 * Provides a base implementation of transform(Collection), simply calling transform(From) for each
 * element and building it into a list.
 */
abstract class BaseDataMapper<From, To> : DataMapper<From, To> {
    override fun transform(models: Collection<From>): List<To> {
        if (models.isEmpty()) return emptyList()
        val tos = ArrayList<To>(models.size)
        models.mapTo(tos) { transform(it) }
        return tos
    }
}
