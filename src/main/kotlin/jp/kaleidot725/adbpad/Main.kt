// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.compose.AppTheme
import jp.kaleidot725.adbpad.component.SplitLayout
import jp.kaleidot725.adbpad.resource.WINDOW_TITLE

@Composable
@Preview
fun App() {
    AppTheme(useDarkTheme = false) {
        Surface {
            SplitLayout(
                leftContent = {
                    Box(modifier = Modifier.width(200.dp).fillMaxHeight().background(Color.White)) {
                        Button(onClick = {}, modifier = Modifier.wrapContentSize().align(Alignment.Center)) {
                        Text("Material1")
                    }
                    }
                },
                rightContent = {
                    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
                        Button(onClick = {}, modifier = Modifier.wrapContentSize().align(Alignment.Center)) {
                        Text("Material2")
                    }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
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
