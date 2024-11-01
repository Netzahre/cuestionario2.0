package com.example.cuestionario_20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bienvenida = findViewById<TextView>(R.id.bienvenida)
        val datos = intent.extras
        val usuario = datos?.getString("usuario")
        val admin = SQLiteHelper(this, "admin", null, 1)
        val comenzar = findViewById<Button>(R.id.desconectar)

        bienvenida.text = "Bienvenido, $usuario"

        val notaTexto = findViewById<TextView>(R.id.nota)
        notaTexto.text = recogerNotaMasAlta(usuario, admin).toString()

        val pregunta1 = Intent(this, pregunta1::class.java)
        pregunta1.putExtra("usuario", usuario)

        comenzar.setOnClickListener {
            startActivity(pregunta1)
        }
    }

    fun recogerNotaMasAlta(usuario: String?, admin: SQLiteHelper): Double {
        val bd = admin.readableDatabase
        val fila = bd.rawQuery("SELECT notaMax FROM Usuarios WHERE usuario=?", arrayOf(usuario))
        return if (fila.moveToFirst()) {
            val nota = fila.getDouble(0)
            fila.close()
            bd.close()
            nota
        } else {
            fila.close()
            bd.close()
            0.0
        }
    }
}
