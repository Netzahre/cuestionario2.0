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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        val respuestasSimpleUsuario = datos?.getStringArray("respuestasSimple")
        val respuestasMultipleUsuario = datos?.get("respuestasMultiple")

        val pregunta6 = arrayOf("Moto triciclo", "Tiendas privadas", "Exoesqueleto", "Camiones")
        val adaptador = ArrayAdapter(this, R.layout.my_menu_dropdown, pregunta6)
        val spinner = findViewById<Spinner>(R.id.respuestaspinpreg6)
        spinner.adapter = adaptador

        val siguiente = findViewById<Button>(R.id.siguiente)

        siguiente.setOnClickListener {
            recogerRespuesta(spinner, respuestasSimpleUsuario)
            val pregunta5 = Intent(this, pregunta5::class.java)
            pregunta5.putExtra("usuario", usuario)
            pregunta5.putExtra("respuestasSimple", respuestasSimpleUsuario)
            startActivity(pregunta5)
        }
    }

    fun recogerRespuesta(spinner: Spinner, respuestasSimpleUsuario: Array<String>?) {
        respuestasSimpleUsuario?.set(3, spinner.selectedItem.toString())
    }

}