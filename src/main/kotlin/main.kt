import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = {exitApplication()}
    ) {
        val content0 = remember { mutableStateOf("") }
        val content1 = remember { mutableStateOf("") }

        Column {
            TextField(
                content0.value, onValueChange = { content0.value = it },
                modifier = Modifier.moveFocusOnTab()
            )
            TextField(
                value = content1.value, onValueChange = { content1.value = it },
                modifier = Modifier.moveFocusOnTab()
            )
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
