/**
 * Override this error lifter to modify the error resolver
 */
var VMStateErrorLifter = { e: Throwable ->
    failed(Exception(e))
}

// region Utils
suspend fun <T : Any> exec(bloc: suspend () -> T): VMState<T> {
    return runCatching { bloc.invoke() }
        .map { success(it) }
        .getOrElse { it.liftError() }
}

fun Throwable.liftError(): VMState<Nothing> {
    return VMStateErrorLifter(this)
}

fun idle() = VMState.Idle
fun loading() = VMState.Loading
fun <T : Any> success(data: T) = VMState.Success(data)
fun failed(e: Exception) = VMState.Failed(e)
// endregion

sealed interface VMState<out T : Any> {
    object Idle : VMState<Nothing>
    object Loading : VMState<Nothing>
    data class Success<T : Any>(val data: T) : VMState<T>
    data class Failed(val e: Exception) : VMState<Nothing>
}