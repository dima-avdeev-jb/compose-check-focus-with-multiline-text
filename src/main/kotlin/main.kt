import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import kotlinx.coroutines.flow.MutableStateFlow

fun main() = run {
    val textFlow = MutableStateFlow("")

    application {
        Window(
            state = WindowState(size = DpSize(350.dp, 500.dp)),
            onCloseRequest = ::exitApplication
        ) {
            Column(
                modifier = Modifier.padding(50.dp)
            ) {
                for (x in 1..5) {
                    val textState = textFlow.collectAsState()
                    val text = remember { textState }
                    TextField(
                        value = text.value,
                        onValueChange = { textFlow.value = it },
                        label = { Text("Test") },
                        singleLine = true,
                        modifier = Modifier.moveFocusOnTab()
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.height(10.dp))
                }
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
