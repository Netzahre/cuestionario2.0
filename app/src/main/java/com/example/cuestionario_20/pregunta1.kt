package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta1 : AppCompatActivity() {

    val respuestasSimpleUsuario = arrayOfNulls<String>(8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        val grupo = findViewById<RadioGroup>(R.id.grupo1)
        val siguiente = findViewById<Button>(R.id.siguiente)

        siguiente.setOnClickListener {
            recogerRespuesta(grupo)
            val pregunta2 = Intent(this, pregunta2::class.java)
            pregunta2.putExtra("usuario", usuario)
            pregunta2.putExtra("respuestasSimple", respuestasSimpleUsuario)
            startActivity(pregunta2)
        }
    }

    fun recogerRespuesta(grupo: RadioGroup) {
        respuestasSimpleUsuario[0] = recuperarRadioPulsado(grupo)
    }

    fun recuperarRadioPulsado(radioGroup: RadioGroup): String {
        val idRadoiElegido = radioGroup.checkedRadioButtonId
        if (idRadoiElegido != -1) {
            val radioElegidoTexto: RadioButton = findViewById(idRadoiElegido)
            return radioElegidoTexto.text.toString()

        } else {
            return "null"
        }
    }
}