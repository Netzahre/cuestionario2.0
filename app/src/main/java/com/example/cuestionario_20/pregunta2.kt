package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        val respuestasSimpleUsuario = datos?.getStringArray("respuestasSimple")

        val pregunta = arrayOf(
            "Detectar entidades sobrenaturales",
            "Servir como una fuente de energía",
            "Sanar heridas",
            "Brindar información sobre el pasado"
        )
        val adaptador = ArrayAdapter(
            this, R.layout.my_menu_dropdown,
            pregunta
        )
        val spinner = findViewById<Spinner>(R.id.respuestaspinpreg2)
        spinner.adapter = adaptador

        val siguiente = findViewById<Button>(R.id.siguiente)

        siguiente.setOnClickListener {
            recogerRespuesta(spinner, respuestasSimpleUsuario)
            val pregunta3 = Intent(this, pregunta3::class.java)
            pregunta3.putExtra("usuario", usuario)
            pregunta3.putExtra("respuestasSimple", respuestasSimpleUsuario)
            startActivity(pregunta3)
        }
    }

    fun recogerRespuesta(spinner: Spinner, respuestasSimpleUsuario: Array<String>?) {
        respuestasSimpleUsuario?.set(1, spinner.selectedItem.toString())
    }
}