package ychescale9.infra.presentation

sealed class NetworkBoundResource<out Result, out ErrorType>

class Loading<out Result, out ErrorType> : NetworkBoundResource<Result, ErrorType>()

data class Success<out Result, out Any>(val data: Result) : NetworkBoundResource<Result, Any>()

data class Failure<out Any, out ErrorType>(val errorType: ErrorType) : NetworkBoundResource<Any, ErrorType>()
