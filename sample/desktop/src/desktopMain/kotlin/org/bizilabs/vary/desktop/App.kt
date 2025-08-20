package org.bizilabs.vary.desktop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.kotlin.fibonacci.org.bizilabs.vary.Vary
import io.github.kotlin.fibonacci.org.bizilabs.vary.vary
import org.bizilabs.vary.models.LocalVarySize
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Vary {
        AppContent()
    }
}

@Composable
fun AppContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        vary({
            xs {
                Content("xs")
            }
            md {
                Content("md")
            }
            lg {
                Content("lg")
            }
            xl {
                Content("xl")
            }
            xxl {
                Content("xxl")
            }
        }) {
            Content("sm")
        }
    }
}

@Composable
fun Content(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text)
        Text(text = "${LocalVarySize.current}")
    }
}
