package org.radonlab.nexus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import org.radonlab.nexus.ui.theme.NexusTheme

class MainActivity : ComponentActivity() {

    private var pendingOverlayStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bridge = RuntimeJsBridge(
            activity = this,
        )
        enableEdgeToEdge()
        setContent {
            NexusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainWebView(
                        bridge = bridge,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (pendingOverlayStart && Settings.canDrawOverlays(this)) {
            pendingOverlayStart = false
            startService(Intent(this, OverlayService::class.java))
        }
    }
}

@Composable
private fun MainWebView(bridge: RuntimeJsBridge, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                addJavascriptInterface(bridge, "NexusAndroid")
                loadUrl("file:///android_asset/index.html")
            }
        },
        modifier = modifier
    )
}