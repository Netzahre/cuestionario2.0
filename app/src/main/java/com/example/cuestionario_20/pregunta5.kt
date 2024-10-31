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
    val respuestasPregunta5 = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        val respuestasSimpleUsuario = datos?.getStringArray("respuestasSimple")

        val checkBoxesPregunta5 = listOf<CheckBox>(
            findViewById(R.id.check1Preg5),
            findViewById(R.id.check2Preg5),
            findViewById(R.id.check3Preg5),
            findViewById(R.id.check4Preg5)
        )

        val siguiente = findViewById<Button>(R.id.siguiente4)

        siguiente.setOnClickListener {
            recogerRespuesta(checkBoxesPregunta5)
            val pregunta6 = Intent(this, pregunta6::class.java)
            pregunta6.putExtra("usuario", usuario)
            pregunta6.putExtra("respuestasSimple", respuestasSimpleUsuario)
            pregunta6.putStringArrayListExtra("respuestasMultiple", respuestasPregunta5)
            startActivity(pregunta6)
        }

    }
    fun recogerRespuesta(checkBoxesPregunta5: List<CheckBox>?) {
        respuestasPregunta5.clear()
        if (checkBoxesPregunta5 != null) {
            for (checkbox in checkBoxesPregunta5) {
                if (checkbox.isChecked) {
                    respuestasPregunta5.add(checkbox.text.toString())
                }
            }
        }
    }
}