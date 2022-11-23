import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        state = WindowState(size = DpSize(350.dp, 500.dp)),
        onCloseRequest = ::exitApplication
    ) {
        Column(
            modifier = Modifier.padding(50.dp)
        ) {
            for (x in 1..5) {
                val text = remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier.moveFocusOnTab()
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Modifier.moveFocusOnTab(
    focusManager: FocusManager = LocalFocusManager.current
) = onPreviewKeyEvent {
    if (it.type == KeyEventType.KeyDown && it.key == Key.Tab) {
        focusManager.moveFocus(
            if (it.isShiftPressed) FocusDirection.Previous
            else FocusDirection.Next
        )
        return@onPreviewKeyEvent true
    }
    false
}
