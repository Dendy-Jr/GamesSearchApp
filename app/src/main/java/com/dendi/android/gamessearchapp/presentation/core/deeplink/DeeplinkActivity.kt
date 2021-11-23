package com.dendi.android.gamessearchapp.presentation.core.deeplink

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import com.dendi.android.gamessearchapp.databinding.DeeplinkActivityBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseActivity

/**
 * @author Dendy-Jr on 22.11.2021
 * olehvynnytskyi@gmail.com
 */
class DeeplinkActivity : BaseActivity() {

    private lateinit var binding: DeeplinkActivityBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeeplinkActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri: Uri? = intent.data
        if (uri != null) {
            val path = uri.toString()
            binding.webViewActivity.apply {
                loadUrl(path)
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    setSupportZoom(true)
                    displayZoomControls = true
                }
            }
        }
    }
}