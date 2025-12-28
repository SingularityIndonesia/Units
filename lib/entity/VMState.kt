
sealed interface VMState<out T : Any> {
    companion object {
        private val VMStateErrorLifter = { e: Throwable ->
            failed(Exception(e))
        }

        fun idle() = Idle
        fun loading() = Loading
        fun <T : Any> success(data: T) = Success(data)
        fun failed(e: Exception) = Failed(e)

        suspend fun <T : Any> exec(
            bloc: suspend () -> T,
            errorLifter: (Throwable) -> Failed = VMStateErrorLifter
        ): VMState<T> {
            return runCatching { bloc.invoke() }
                .map { success(it) }
                .getOrElse{ errorLifter(it) }
        }
    }

    object Idle : VMState<Nothing>
    object Loading : VMState<Nothing>
    data class Success<T : Any>(val data: T) : VMState<T>
    data class Failed(val e: Exception) : VMState<Nothing>
}