package org.bizilabs.samples.vary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kotlin.fibonacci.org.bizilabs.vary.Vary
import io.github.kotlin.fibonacci.org.bizilabs.vary.vary
import org.bizilabs.samples.vary.ui.theme.VaryTheme
import org.bizilabs.vary.models.LocalVarySize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VaryTheme {
                Vary {
                    AppContent()
                }
            }
        }
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