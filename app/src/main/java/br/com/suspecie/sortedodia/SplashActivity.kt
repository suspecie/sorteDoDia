package br.com.suspecie.sortedodia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*
            Pausa para a execução do app
         */
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, CadastroActivity::class.java))
            finish()
        }, 5000)
    }
}
