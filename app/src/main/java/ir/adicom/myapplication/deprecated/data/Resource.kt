package ir.adicom.myapplication.deprecated.data

sealed class Resource<T> {
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val message: String): Resource<T>()
}
