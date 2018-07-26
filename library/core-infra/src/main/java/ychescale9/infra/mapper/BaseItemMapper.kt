package ychescale9.infra.mapper

interface BaseItemMapper<From, To> {
    fun transform(item: From, vararg params: Any): To
}
