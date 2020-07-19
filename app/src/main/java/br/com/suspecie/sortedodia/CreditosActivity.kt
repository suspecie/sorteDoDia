package br.com.suspecie.sortedodia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_creditos.*

class CreditosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)

        // Recuperando sites da intent
        val freepik = intent.getStringExtra("siteFreepik")
        val gitHub = intent.getStringExtra("gitHubSu")

        txtCreditosIcons.setOnClickListener {
            startActivity(Intent(this@CreditosActivity, WebActivity::class.java)
                .apply {
                    putExtra("site", freepik)
                }
            )
        }

        txtCreditosSu.setOnClickListener {
            startActivity(Intent(this@CreditosActivity, WebActivity::class.java)
                .apply {
                    putExtra("site", gitHub)
                }
            )
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@CreditosActivity, MainActivity::class.java))
        finishAffinity()
    }
}
