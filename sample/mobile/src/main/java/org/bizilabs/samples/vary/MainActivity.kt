package org.bizilabs.samples.vary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.bizilabs.vary.Vary
import org.bizilabs.vary.vary
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
            sm {
                Content("sm")
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
            Content("xs")
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