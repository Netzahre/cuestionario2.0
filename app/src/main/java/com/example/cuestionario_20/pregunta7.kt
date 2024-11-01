package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras
        val usuario = datos?.getString("usuario") ?: "Usuario Anónimo"
        val respuestasSimpleUsuario = datos?.getString("respuestasSimple") ?: ""

        val siguiente = findViewById<Button>(R.id.siguiente2)
        val grupo = findViewById<RadioGroup>(R.id.grupo2)

        siguiente.setOnClickListener {
            val respuestasActualizadas = recogerRespuesta(grupo, respuestasSimpleUsuario)
            val pregunta8 = Intent(this, pregunta8::class.java)
            pregunta8.putExtra("usuario", usuario)
            pregunta8.putExtra("respuestasSimple", respuestasActualizadas)
            pregunta8.putExtra("respuestasPreg5", datos?.getString("respuestasPreg5"))
            startActivity(pregunta8)
        }
    }

    private fun recogerRespuesta(grupo: RadioGroup, respuestasSimpleUsuario: String): String {
        val respuestasArray = respuestasSimpleUsuario.split(";").toMutableList()
        respuestasArray.add(recuperarRadioPulsado(grupo))
        return respuestasArray.joinToString(";")
    }

    private fun recuperarRadioPulsado(radioGroup: RadioGroup): String {
        val idRadioElegido = radioGroup.checkedRadioButtonId
        return if (idRadioElegido != -1) {
            val radioElegidoTexto: RadioButton = findViewById(idRadioElegido)
            radioElegidoTexto.text.toString()
        } else {
            "Respuesta no seleccionada"
        }
    }
}
