package utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

// region KeyboardOptions composer
infix operator fun KeyboardOptions.plus(imeAction: ImeAction): KeyboardOptions {
    return copy(imeAction = imeAction)
}

infix operator fun KeyboardOptions.plus(keyboardType: KeyboardType): KeyboardOptions {
    return copy(keyboardType = keyboardType)
}
// endregion