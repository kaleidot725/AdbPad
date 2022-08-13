// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.compose.AppTheme
import jp.kaleidot725.adbpad.theme.WINDOW_TITLE

@Composable
@Preview
fun App() {
    AppTheme(useDarkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {}) {
                Text("Material Test")
            }
        }
    }
}

fun main() = application {
    Window(
        title = WINDOW_TITLE,
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
