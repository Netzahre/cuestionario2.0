package com.example.cuestionario_20

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datos = intent.extras ?: return
        val usuario = datos.getString("usuario") ?: "Usuario Anónimo"

        val respuestasUser = datos.getString("respuestasSimple")?.split(";")?.toList() ?: emptyList()
        val correccionRespUnica = datos.getString("correccionesSimple")?.split(";")?.toList() ?: emptyList()
        var respuestasPreg5 = datos.getString("respuestasPreg5")
        var respuestasPreg9 = datos.getString("respuestasPreg9")
        val correcionesPreg5 = datos.getString("correccionesPreg5")
        val correcionesPreg9 = datos.getString("correccionesPreg9")

        if (respuestasPreg5 != null) {
            if (respuestasPreg5.isBlank()) {
                respuestasPreg5 = "Respuesta no seleccionada"
            }
        }

        if (respuestasPreg9 != null) {
            if (respuestasPreg9.isBlank()) {
                respuestasPreg9 = "Respuesta no seleccionada"
            }
        }
        val correccionMultiUnidas = ("$correcionesPreg5;$correcionesPreg9").split(";").toList()
        val respuestasMultiUnidas = ("$respuestasPreg5;$respuestasPreg9").split(";").toList()

        val nota = datos.getString("nota")?.toDoubleOrNull() ?: 0.0
        val aciertos = datos.getString("aciertos")?.toIntOrNull() ?: 0


        val admin = SQLiteHelper(this, "admin", null, 1)
        val desconectar = findViewById<Button>(R.id.desconectar)
        val nombreUser = findViewById<TextView>(R.id.bienvenida)
        nombreUser.text = "Bienvenido, $usuario"

        val notaText = findViewById<TextView>(R.id.nota)
        val aciertosText = findViewById<TextView>(R.id.nota2)

        notaText.text = nota.toString()
        aciertosText.text = aciertos.toString()

        val record = findViewById<TextView>(R.id.record)
        record.text = mejoroNota(admin, nota, usuario)

        val respuestas = findViewById<TextView>(R.id.respuestas)
        val respuestasMulti = findViewById<TextView>(R.id.respuestasMulti)

        val resultados = findViewById<TextView>(R.id.resultados)
        val resultadosMulti = findViewById<TextView>(R.id.resultadosMulti)

        rellenarSimple(respuestas, resultados, respuestasUser, correccionRespUnica)
        rellenarMulti(respuestasMulti , resultadosMulti, respuestasMultiUnidas, correccionMultiUnidas)

        desconectar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun mejoroNota(admin: SQLiteHelper, notaNueva: Double, usuario: String?): String {
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("SELECT notaMax FROM Usuarios WHERE usuario=?", arrayOf(usuario))

        if (fila.moveToFirst()) {
            val notaAnterior = fila.getDouble(0)
            if (notaNueva > notaAnterior) {
                val registro = ContentValues().apply {
                    put("notaMax", notaNueva)
                }
                bd.update("Usuarios", registro, "usuario=?", arrayOf(usuario))
                return "¡Has superado tu nota anterior!"
            }
        } else {
            Toast.makeText(this, "Ha ocurrido algún error al acceder a la base de datos", Toast.LENGTH_SHORT).show()
        }
        fila.close()
        return ""
    }

    private fun rellenarSimple(
        respuestas: TextView,
        resultados: TextView,
        respuestasUser: List<String>,
        correccionesCompleto: List<String>
    ) {
        respuestas.text = respuestasUser.joinToString("\n")
        resultados.text = correccionesCompleto.joinToString("\n")
    }

    private fun rellenarMulti(
        respuestas: TextView,
        resultados: TextView,
        respuestasUser: List<String>,
        correccionesCompleto: List<String>
    ) {
        respuestas.text = respuestasUser.joinToString("\n")
        resultados.text = correccionesCompleto.joinToString("\n")
    }
}
