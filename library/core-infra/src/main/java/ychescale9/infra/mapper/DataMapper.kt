package ychescale9.infra.mapper

interface DataMapper<From, To> {
    fun transform(model: From, vararg params: Any): To
    fun transform(models: Collection<From>): Collection<To>
}
