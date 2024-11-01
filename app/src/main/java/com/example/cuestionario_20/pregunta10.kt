package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pregunta10 : AppCompatActivity() {

    private val respuestasCorrectasUnica = arrayOf(
        "Sam Porter Bridges", "Detectar entidades sobrenaturales",
        "América", "Higgs Monaghan", "Tiendas privadas", "Conexiones y aislamiento",
        "Acelera el deterioro y envejecimiento", "Reconectar a América"
    )
    private val respuestasCorrectasPreg5 = arrayOf("BTs", "EV")
    private val respuestasCorrectasPreg9 = arrayOf("Facilitar la conexión entre jugadores en línea", "Permitir teletransportarse")

    private val listaFinalSimple = ArrayList<String>()
    private val listaFinalPreg5 = ArrayList<String>()
    private val listaFinalPreg9 = ArrayList<String>()

    private var cantidad = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta10)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras ?: return
        val usuario = datos.getString("usuario") ?: return
        val respuestasSimpleUsuario = datos.getString("respuestasSimple") ?: ""
        val respuestasPreg5 = datos.getString("respuestasPreg5") ?: ""
        val respuestasPreg9 = datos.getString("respuestasPreg9") ?: ""

        val pregunta = arrayOf(
            "Reconectar a América",
            "Destruir a los BTs",
            "Derrotar a Higgs",
            "Salvar a Amelie"
        )
        val adaptador = ArrayAdapter(this, R.layout.my_menu_dropdown, pregunta)
        val spinner = findViewById<Spinner>(R.id.respuestaspinpreg4)
        spinner.adapter = adaptador

        val siguiente = findViewById<Button>(R.id.siguiente)

        siguiente.setOnClickListener {
            val respuestasActualizadas = recogerRespuesta(spinner, respuestasSimpleUsuario)
            val nota = calcularNota(respuestasActualizadas, respuestasPreg5, respuestasPreg9)
            val aciertos = cantidad.toString()

            Toast.makeText(
                this,
                "Tu puntuación es $nota con $aciertos aciertos.",
                Toast.LENGTH_LONG
            ).show()

            val correccionesSimple = listaFinalSimple.joinToString(";")
            val correccionesPreg5 = listaFinalPreg5.joinToString(";")
            val correccionesPreg9 = listaFinalPreg9.joinToString(";")
            val resultados = Intent(this, ResultadosActivity::class.java).apply {
                putExtra("usuario", usuario)
                putExtra("nota", nota.toString())
                putExtra("aciertos", aciertos)
                putExtra("respuestasSimple", respuestasActualizadas)
                putExtra("respuestasPreg5", respuestasPreg5)
                putExtra("respuestasPreg9", respuestasPreg9)
                putExtra("correccionesSimple", correccionesSimple)
                putExtra("correccionesPreg5", correccionesPreg5)
                putExtra("correccionesPreg9", correccionesPreg9)
            }

            startActivity(resultados)
        }
    }

    fun recogerRespuesta(spinner: Spinner, respuestasSimpleUsuario: String): String {
        val respuestasArray = respuestasSimpleUsuario.split(";").toMutableList()
        respuestasArray.add(spinner.selectedItem.toString())
        return respuestasArray.joinToString(";")
    }

    private fun calcularNota(
        respuestasSimplesUsuario: String,
        respuestasPreg5: String,
        respuestasPreg9: String
    ): Double {
        var nota = 0.0
        val respuestasSimples = respuestasSimplesUsuario.split(";").toList()
        for (i in respuestasCorrectasUnica.indices) {
            if (respuestasSimples[i] == respuestasCorrectasUnica[i]) {
                nota++
                cantidad++
                listaFinalSimple.add("¡Correcto!")
            } else {
                listaFinalSimple.add("La respuesta correcta era ${respuestasCorrectasUnica[i]}")
            }
        }

        nota += verificarRespuestas(respuestasPreg5, respuestasCorrectasPreg5, listaFinalPreg5)
        nota += verificarRespuestas(respuestasPreg9, respuestasCorrectasPreg9, listaFinalPreg9)

        return nota
    }

    private fun verificarRespuestas(
        respuestasUsuario: String,
        respuestasCorrectas: Array<String>,
        listaFinal: ArrayList<String>
    ): Double {
        var notaPregunta = 0.0
        if (respuestasUsuario.isBlank()) {
            listaFinal.add("La respuesta correcta era ${respuestasCorrectas.joinToString(",")}")
            return 0.0;
        }
        val respuestasUser = respuestasUsuario.split(";").toList()
        for (i in respuestasCorrectas.indices) {
            if (i < respuestasUser.size) {
                if (respuestasUser[i] == respuestasCorrectas[i]) {
                    notaPregunta += 0.5
                    cantidad++
                    listaFinal.add("¡Correcto!")
                } else {
                    listaFinal.add("La respuesta correcta era ${respuestasCorrectas[i]}")
                    notaPregunta -= 0.5
                }
            }
        }
        return if (notaPregunta < 0) 0.0 else notaPregunta
    }
}
