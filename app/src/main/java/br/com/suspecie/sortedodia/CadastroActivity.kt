package br.com.suspecie.sortedodia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_creditos.*
import org.w3c.dom.Text

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        /*
            Criando o spinner com as opções de cores
         */
        val colorList = arrayListOf("Escolha sua cor da sorte", "Azul", "Rosa", "Amarelo", "Laranja")

        // Criando adapter para o spinner
        val spinnerAdapter = ArrayAdapter(
            this@CadastroActivity,
            android.R.layout.simple_spinner_dropdown_item,
            colorList
        )

        // Link do adapter com o spinner
        spnCadastroColor.adapter = spinnerAdapter


        /*
            Iniciando o cadastro
         */

        btnCadastroCadastrar.setOnClickListener {

            // Obtendo os dados escolhidos

            val name = edtCadastroName.text.toString()
            val email = edtCadastroEmail.text.toString()
            val color = spnCadastroColor.selectedItem.toString()

            // Verifica se todos os campos foram preenchidos e grava as escolhas no shared preferences

            if (name.isEmpty()) {
                edtCadastroName.error = "Campo obrigatório"
                edtCadastroName.requestFocus()
            } else if (email.isEmpty()) {
                edtCadastroEmail.error = "Campo obrigatório"
                edtCadastroEmail.requestFocus()
            } else if (color == "Escolha sua cor da sorte") {

                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos")
                    .setPositiveButton("OK") {_,_ ->
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            } else if (name.isEmpty() && email.isEmpty() && color == "Escolha sua cor da sorte") {

                // Criando alerta para mostrar erro

                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos")
                    .setPositiveButton("OK") {_,_ ->
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            } else {

                // Gravando os dados no shared preferences
                getSharedPreferences("cadastro-$email", Context.MODE_PRIVATE)
                    .edit()
                    .apply {
                        putString("nome", name)
                        putString("email", email)
                        putString("cor", color)
                    }
                    .apply()

                // Criando toast de sucesso do cadastro

                Toast.makeText(
                    this@CadastroActivity,
                    "Cadastro realizado com sucesso!",
                    Toast.LENGTH_LONG
                ).show()

                // Limpando os campos depois que o cadastro foi realizado

                edtCadastroName.text.clear()
                edtCadastroEmail.text.clear()
                spnCadastroColor.setSelection(0)

                // Abrindo a tela Main
                startActivity(Intent(this@CadastroActivity, MainActivity::class.java))

                // Fechando todas as telas do empilhamento
                finishAffinity()

            }
        }

        btnCadastroRegister.setOnClickListener {
            // Abrindo a tela Main
            startActivity(Intent(this@CadastroActivity, MainActivity::class.java))

            // Fechando todas as telas do empilhamento
            finishAffinity()
        }
    }
}
