package org.radonlab.nexus

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.lynx.tasm.LynxViewBuilder
import com.lynx.xelement.XElementBehaviors
import org.radonlab.nexus.ui.theme.NexusTheme

class MainActivity : ComponentActivity() {

    private var pendingOverlayStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LynxHost(
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
private fun LynxHost(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            val viewBuilder = LynxViewBuilder()
            viewBuilder.addBehaviors(XElementBehaviors().create())
            viewBuilder.setTemplateProvider(AssetsTemplateProvider(context))
            val lynxView = viewBuilder.build(context)
            lynxView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            lynxView.renderTemplateUrl(LYNX_ENTRY_BUNDLE, "")
            lynxView
        },
        modifier = modifier
    )
}

private const val LYNX_ENTRY_BUNDLE = "main.lynx.bundle"
