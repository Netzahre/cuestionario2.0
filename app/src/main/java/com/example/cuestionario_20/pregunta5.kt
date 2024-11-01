package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras
        val usuario = datos?.getString("usuario") ?: "Usuario Anónimo"
        val respuestasSimpleUsuario = datos?.getString("respuestasSimple") ?: ""

        val checkBoxesPregunta5 = listOf<CheckBox>(
            findViewById(R.id.check1Preg5),
            findViewById(R.id.check2Preg5),
            findViewById(R.id.check3Preg5),
            findViewById(R.id.check4Preg5)
        )

        val siguiente = findViewById<Button>(R.id.siguiente4)

        siguiente.setOnClickListener {
            val respuestasPregunta5 = recogerRespuesta(checkBoxesPregunta5)
            val pregunta6 = Intent(this, pregunta6::class.java)
            pregunta6.putExtra("usuario", usuario)
            pregunta6.putExtra("respuestasSimple", respuestasSimpleUsuario)
            pregunta6.putExtra("respuestasPreg5", respuestasPregunta5)
            startActivity(pregunta6)
        }
    }

    private fun recogerRespuesta(checkBoxesPregunta5: List<CheckBox>): String {
        val respuestasSeleccionadas = mutableListOf<String>()
        for (checkbox in checkBoxesPregunta5) {
            if (checkbox.isChecked) {
                respuestasSeleccionadas.add(checkbox.text.toString())
            }
        }
        return respuestasSeleccionadas.joinToString(";")
    }
}
