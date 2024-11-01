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

class pregunta6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta6)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datos = intent.extras
        val usuario = datos?.getString("usuario") ?: "Usuario Anónimo"
        val respuestasSimpleUsuario = datos?.getString("respuestasSimple") ?: ""
        val respuestasPreg5 = datos?.getString("respuestasPreg5") ?: ""

        val opcionesPregunta6 = arrayOf("Moto triciclo", "Tiendas privadas", "Exoesqueleto", "Camiones")
        val adaptador = ArrayAdapter(this, R.layout.my_menu_dropdown, opcionesPregunta6)
        val spinner = findViewById<Spinner>(R.id.respuestaspinpreg6)
        spinner.adapter = adaptador

        val siguiente = findViewById<Button>(R.id.siguiente)

        siguiente.setOnClickListener {
            val respuestasActualizadas = recogerRespuesta(spinner, respuestasSimpleUsuario)
            val pregunta7 = Intent(this, pregunta7::class.java)
            pregunta7.putExtra("usuario", usuario)
            pregunta7.putExtra("respuestasSimple", respuestasActualizadas)
            pregunta7.putExtra("respuestasPreg5", respuestasPreg5)
            startActivity(pregunta7)
        }
    }

    private fun recogerRespuesta(spinner: Spinner, respuestasSimpleUsuario: String): String {
        val respuestasArray = respuestasSimpleUsuario.split(";").toMutableList()
        respuestasArray.add(spinner.selectedItem.toString())
        return respuestasArray.joinToString(";")
    }
}
