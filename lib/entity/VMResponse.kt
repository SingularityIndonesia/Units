sealed interface VMState<T : Any> {
    object Idle : VMState<Nothing>
    object Loading : VMState<Nothing>
    data class Success<T : Any>(val data: T) : VMState<T>
    data class Failed(val e: Exception) : VMState<Nothing>
}

fun idle() = VMState.Idle
fun loading() = VMState.Loading
fun <T : Any> success(data: T) = VMState.Success(data)
fun failed(e: Exception) = VMState.Failed(e)