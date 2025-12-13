package utils

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert

val DefaultPhoneOutputTransformation = OutputTransformation {
    if (originalText.length > 3)
        insert(3, "-")

    if (originalText.length > 7)
        insert(8, "-")
}