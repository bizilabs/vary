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
import androidx.compose.ui.unit.sp
import org.bizilabs.samples.vary.ui.theme.VaryTheme
import org.bizilabs.vary.Vary
import org.bizilabs.vary.models.LocalVaryWidth
import org.bizilabs.vary.vary

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
        horizontalAlignment = Alignment.CenterHorizontally,
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            fontSize =
                vary(24.sp) {
                    sm(30.sp)
                    md(48.sp)
                    lg(56.sp)
                },
        )
        val repeat =
            vary(1) {
                sm(2)
                md(3)
                lg(4)
                xl(5)
                xxl(6)
            }
        repeat(repeat) {
            Text(text = "${LocalVaryWidth.current}")
        }
    }
}
