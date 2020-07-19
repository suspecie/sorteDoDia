package br.com.suspecie.sortedodia

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
            Criando array com a lista de frases da sorte
         */

        val luckList = arrayListOf(
            "Se você pode sonhar, você pode fazer. Walt Disney",
            "Cada segundo é tempo para mudar tudo para sempre. Charles Chaplin",
            "Seja a mudança que você quer ver no mundo. Dalai Lama",
            "Aquilo que se faz por amor está sempre além do bem e do mal. Friedrich Nietzche",
            "Com grandes poderes, vem grandes responsabilidades. Caio Fernando Abreu"
        )

        /*
            Iniciando a sorte do dia
         */


        btnMainLuck.setOnClickListener {

            /*
                Recuperando os dados do shared preferences
            */

            val emailRecovered = edtMainEmail.text.toString();

            val myPreferences = getSharedPreferences("cadastro-$emailRecovered", Context.MODE_PRIVATE)

            // Verifica se o campo foi preenchido

            if (emailRecovered.isEmpty()) {
                // Criando alerta para mostrar erro

                edtMainEmail.error = "Campo obrigatório"
                edtMainEmail.requestFocus()
            } else {

                // Recuperando uma frase aleatória
                val randomLuck = Random.nextInt(0, luckList.lastIndex)
                val luckOfDay = luckList.get(randomLuck)

                // Recuperando cor e nome do shared preferences
                val nameRecovered = myPreferences.getString("nome", "Ei, você!")
                val colorRecovered = myPreferences.getString("cor", "Chave não encontrada")

                // Definindo cor do background
                if ( colorRecovered == "Laranja" ) {
                    window.decorView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorOrange))
                } else if ( colorRecovered == "Rosa") {
                    window.decorView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorPink))
                } else if ( colorRecovered == "Azul") {
                    window.decorView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorBlue))
                } else if ( colorRecovered == "Amarelo") {
                    window.decorView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorYellow))
                } else {
                    window.decorView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorWhite))
                }

                // Mostrar o alerta
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("$nameRecovered")
                    .setMessage("$luckOfDay")
                    .setPositiveButton("OK") {_,_ ->
                        // limpa os campos
                        edtMainEmail.text.clear()
                    }
                    .setCancelable(false)
                    .create()
                    .show()

            }


        }

        btnMainCredits.setOnClickListener {
            // Abrindo a tela Main
            startActivity(Intent(this@MainActivity, CreditosActivity::class.java)
                .apply {
                    putExtra("siteFreepik", resources.getString(R.string.siteFreepik))
                    putExtra("gitHubSu", resources.getString(R.string.siteGitHubSu))
                }
            )

            // Fechando todas as telas do empilhamento
            finishAffinity()
        }


    }
}
