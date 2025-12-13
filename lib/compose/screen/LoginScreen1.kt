package screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    LoginScreen {

    }
}

@Composable
fun LoginScreen(
    userName: TextFieldState = remember { TextFieldState() },
    password: TextFieldState = remember { TextFieldState() },
    onLogin: () -> Unit = {}
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Input(
                    modifier = Modifier.width(300.dp),
                    state = userName,
                    hint = "username"
                )

                Input(
                    modifier = Modifier.width(300.dp),
                    state = password,
                    hint = "password"
                )

                Button(
                    modifier = Modifier.defaultMinSize(300.dp, 0.dp),
                    onClick = onLogin
                ) {
                    Text("Login")
                }
            }
        }
    }
}

@Composable
private fun Input(
    modifier: Modifier,
    state: TextFieldState,
    hint: String
) {
    BasicTextField(
        modifier = modifier,
        state = state,
        decorator = TextFieldDecorator { innerText ->
            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(16.dp)
            ) {
                if (state.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                innerText()
            }
        },
    )
}