package molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.DefaultPhoneOutputTransformation
import utils.plus

@Preview
@Composable
fun PhoneNumberInput1Preview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PhoneNumberInput1(
            modifier = Modifier.width(220.dp),
            state = rememberTextFieldState("89520000366"),
            countryCode = "+62",
            hint = "895-xxxx-xxxx",
            outputTransformation = DefaultPhoneOutputTransformation,
            keyboardOptions = KeyboardOptions.Default +
                    KeyboardType.Number +
                    ImeAction.Next
        )
    }
}

@Composable
fun PhoneNumberInput1(
    modifier: Modifier = Modifier,
    state: TextFieldState = remember { TextFieldState() },
    countryCode: String = "",
    hint: String = "",
    outputTransformation: OutputTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BasicTextField(
        modifier = modifier,
        state = state,
        outputTransformation = outputTransformation,
        keyboardOptions = keyboardOptions,
        decorator = TextFieldDecorator { innerText ->
            Row(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (countryCode.isNotEmpty()) {
                    Text(countryCode)
                    Spacer(
                        modifier = Modifier.width(1.dp)
                            .height(18.dp)
                            .background(MaterialTheme.colorScheme.onSurface)
                    )
                }

                Box {
                    if (state.text.isEmpty()) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    innerText()
                }
            }
        },
    )
}