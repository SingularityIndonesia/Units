
sealed interface VMState<out T : Any> {
    companion object {
        fun idle() = VMState.Idle
        fun loading() = VMState.Loading
        fun <T : Any> success(data: T) = VMState.Success(data)
        fun failed(e: Exception) = VMState.Failed(e)

        val VMStateErrorLifter = { e: Throwable ->
            failed(Exception(e))
        }

        suspend fun <T : Any> exec(
            bloc: suspend () -> T,
            errorLifter: (Throwable) -> VMState.Failed = VMStateErrorLifter
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