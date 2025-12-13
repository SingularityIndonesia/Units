import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.insert

val DEFAULT_PHONE_OUTPUT_TRANSFORMATION = OutputTransformation {
    if (originalText.length > 3)
        insert(3, "-")

    if (originalText.length > 7)
        insert(8, "-")
}