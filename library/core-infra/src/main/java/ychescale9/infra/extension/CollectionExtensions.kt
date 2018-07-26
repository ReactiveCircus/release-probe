package ychescale9.infra.extension

/**
 * Compare whether 2 collections are the same ignoring order
 */
infix fun <T> Collection<T>.sameContentWith(collection: Collection<T>) = collection.let {
    this.size == it.size && this.containsAll(it)
}
