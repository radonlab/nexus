package org.radonlab.nexus

import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.activity.ComponentActivity

class RuntimeJsBridge(
    private val activity: ComponentActivity,
) {
    @JavascriptInterface
    fun showToast(message: String) {
        activity.runOnUiThread {
            Toast.makeText(activity.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun showNotification(title: String, body: String) {
    }

    @JavascriptInterface
    fun startOverlay() {
    }

    @JavascriptInterface
    fun stopOverlay() {
    }
}
