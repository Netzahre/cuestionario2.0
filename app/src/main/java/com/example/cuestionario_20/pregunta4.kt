package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        var respuestasSimpleUsuario = datos?.getString("respuestasSimple") ?: ""

        val pregunta = arrayOf("Heartman", "Fragile", "Higgs Monaghan", "Amelie")
        val adaptador = ArrayAdapter(this, R.layout.my_menu_dropdown, pregunta)
        val spinner = findViewById<Spinner>(R.id.respuestaspinpreg4)
        spinner.adapter = adaptador

        val siguiente = findViewById<Button>(R.id.siguiente)

        siguiente.setOnClickListener {
            respuestasSimpleUsuario = recogerRespuesta(spinner, respuestasSimpleUsuario)
            val pregunta5 = Intent(this, pregunta5::class.java)
            pregunta5.putExtra("usuario", usuario)
            pregunta5.putExtra("respuestasSimple", respuestasSimpleUsuario)
            startActivity(pregunta5)
        }
    }

    fun recogerRespuesta(spinner: Spinner, respuestasSimpleUsuario: String): String {
        val respuestasArray = respuestasSimpleUsuario.split(";").toMutableList()
        respuestasArray.add(spinner.selectedItem.toString())
        return respuestasArray.joinToString(";")
    }
}
