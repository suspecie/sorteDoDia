package br.com.suspecie.sortedodia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // Recuperando sites da intent
        val site = intent.getStringExtra("site")

        //habilitando o javascript no webView
        wbvSite.settings.javaScriptEnabled = true
        wbvSite.loadUrl(site)
        //fala que o webview vai ser aberto através da classe que criamos
        wbvSite.webViewClient = WebViewClient()
    }
}
