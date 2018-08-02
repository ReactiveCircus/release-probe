package ychescale9.infra.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(crossinline block: () -> T): T {
    val viewModelClass = T::class.java
    val factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(viewModelClass) -> block() as T
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
    return ViewModelProvider(this, factory).get(viewModelClass)
}
