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

class pregunta3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        var respuestasSimpleUsuario = datos?.getString("respuestasSimple") ?: ""
        val siguiente = findViewById<Button>(R.id.siguiente2)
        val grupo = findViewById<RadioGroup>(R.id.grupo2)

        siguiente.setOnClickListener {
            respuestasSimpleUsuario = recogerRespuesta(grupo, respuestasSimpleUsuario)
            val pregunta4 = Intent(this, pregunta4::class.java)
            pregunta4.putExtra("usuario", usuario)
            pregunta4.putExtra("respuestasSimple", respuestasSimpleUsuario)
            startActivity(pregunta4)
        }
    }

    fun recogerRespuesta(grupo: RadioGroup, respuestasSimpleUsuario: String): String {
        val respuestasArray = respuestasSimpleUsuario.split(";").toMutableList()
        respuestasArray.add(recuperarRadioPulsado(grupo))
        return respuestasArray.joinToString(";")
    }

    fun recuperarRadioPulsado(radioGroup: RadioGroup): String {
        val idRadioElegido = radioGroup.checkedRadioButtonId
        return if (idRadioElegido != -1) {
            val radioElegidoTexto: RadioButton = findViewById(idRadioElegido)
            radioElegidoTexto.text.toString()
        } else {
            "Respuesta no seleccionada"
        }
    }
}
