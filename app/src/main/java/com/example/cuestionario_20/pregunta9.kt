package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta9 : AppCompatActivity() {
    private var respuestasPreg9 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta9)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras
        val usuario = datos?.getString("usuario") ?: "Usuario Anónimo"
        val respuestasSimpleUsuario = datos?.getString("respuestasSimple") ?: ""
        val respuestasPreg5 = datos?.getString("respuestasPreg5")

        val checkBoxesPregunta9 = listOf(
            findViewById<CheckBox>(R.id.check1Preg9),
            findViewById<CheckBox>(R.id.check2Preg9),
            findViewById<CheckBox>(R.id.check3Preg9),
            findViewById<CheckBox>(R.id.check4Preg9)
        )

        val siguiente = findViewById<Button>(R.id.siguiente4)

        siguiente.setOnClickListener {
            respuestasPreg9 = recogerRespuesta(checkBoxesPregunta9)
            val pregunta10 = Intent(this, pregunta10::class.java)
            pregunta10.putExtra("usuario", usuario)
            pregunta10.putExtra("respuestasSimple", respuestasSimpleUsuario)
            pregunta10.putExtra("respuestasPreg5", respuestasPreg5)
            pregunta10.putExtra("respuestasPreg9", respuestasPreg9)
            startActivity(pregunta10)
        }
    }
}

private fun recogerRespuesta(checkBoxesPregunta9: List<CheckBox>): String {
    val respuestasSeleccionadas = mutableListOf<String>()
    for (checkbox in checkBoxesPregunta9) {
        if (checkbox.isChecked) {
            respuestasSeleccionadas.add(checkbox.text.toString())
        }
    }
    return respuestasSeleccionadas.joinToString(";")
}
