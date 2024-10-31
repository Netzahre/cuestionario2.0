package com.example.cuestionario_20


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val respuestasSimpleUsuario = arrayOfNulls<String>(8)
    val respuestasMultiplesUsuario = mutableListOf<MutableList<String>>()
    val respuestasCorrectasUnica = arrayOf(
        "Sam Porter Bridges", "Detectar entidades sobrenaturales",
        "América", "Higgs Monaghan", "Tiendas privadas", "Conexiones y aislamiento",
        "Acelera el deterioro y envejecimiento", "Reconectar a América"
    )

    val respuestasCorrectasMultiopcion = arrayOf(
        arrayOf("BTs", "EV"),
        arrayOf("Facilitar la conexión entre jugadores en línea", "Permitir teletransportarse"),
        arrayOf()
    )
    var cantidad = 0;

    lateinit var indicadoresCorrectoUnica: Array<TextView>
    lateinit var indicadoresCorrectoMultiple: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val pregunta1 = arrayOf(
            "Detectar entidades sobrenaturales",
            "Servir como una fuente de energía",
            "Sanar heridas",
            "Brindar información sobre el pasado"
        )
        val adaptador1 = ArrayAdapter(
            this, R.layout.my_menu_dropdown,
            pregunta1
        )
        val spinner1 = findViewById<Spinner>(R.id.respuestaspinpreg6)
        spinner1.adapter = adaptador1

        val pregunta4 = arrayOf("Heartman", "Fragile", "Higgs Monaghan", "Amelie")
        val adaptador2 = ArrayAdapter(this, R.layout.my_menu_dropdown, pregunta4)
        val spinner2 = findViewById<Spinner>(R.id.respuestaspinpreg6)
        spinner2.adapter = adaptador2

        val pregunta6 = arrayOf("Moto triciclo", "Tiendas privadas", "Exoesqueleto", "Camiones")
        val adaptador3 = ArrayAdapter(this, R.layout.my_menu_dropdown, pregunta6)
        val spinner3 = findViewById<Spinner>(R.id.respuestaspinpreg6)
        spinner3.adapter = adaptador3

        val pregunta10 = arrayOf(
            "Reconectar a América",
            "Destruir a los BTs",
            "Derrotar a Higgs",
            "Salvar a Amelie"
        )
        val adaptador4 = ArrayAdapter(this, R.layout.my_menu_dropdown, pregunta10)
        val spinner4 = findViewById<Spinner>(R.id.respuestaspinpreg10)
        spinner4.adapter = adaptador4
//
//        val grupo1 = findViewById<RadioGroup>(R.id.grupo1)
//        val grupo2 = findViewById<RadioGroup>(R.id.grupo2)
        val grupo3 = findViewById<RadioGroup>(R.id.grupo3)
        val grupo4 = findViewById<RadioGroup>(R.id.grupo4)

        val checkBoxesPregunta5 = listOf<CheckBox>(
            findViewById(R.id.check1Preg5),
            findViewById(R.id.check2Preg5),
            findViewById(R.id.check3Preg5),
            findViewById(R.id.check4Preg5)
        )

        val checkBoxesPregunta9 = listOf<CheckBox>(
            findViewById(R.id.check1Preg9),
            findViewById(R.id.check2Preg9),
            findViewById(R.id.check3Preg9),
            findViewById(R.id.check4Preg9)
        )

        val corregir = findViewById<Button>(R.id.corregir)

//        val resultado1 = findViewById<TextView>(R.id.resultado1)
//        val resultado2 = findViewById<TextView>(R.id.resultado2)
//        val resultado3 = findViewById<TextView>(R.id.resultado3)
//        val resultado4 = findViewById<TextView>(R.id.resultado4)
//        val resultado5 = findViewById<TextView>(R.id.resultado5)
//        val resultado6 = findViewById<TextView>(R.id.resultado6)
//        val resultado7 = findViewById<TextView>(R.id.resultado7)
//        val resultado8 = findViewById<TextView>(R.id.resultado8)
//        val resultado9 = findViewById<TextView>(R.id.resultado9)
//        val resultado10 = findViewById<TextView>(R.id.resultado10)
//
//        indicadoresCorrectoUnica = arrayOf(resultado1, resultado2, resultado3, resultado4, resultado6,
//            resultado7, resultado8, resultado10)
//
//        indicadoresCorrectoMultiple = arrayOf(resultado5, resultado9)
//
//
//        val aciertos = findViewById<TextView>(R.id.cantidad)
//        val resultadoTotal = findViewById<TextView>(R.id.numero)
//        corregir.setOnClickListener { corregir(grupo1, grupo2, grupo3, grupo4, spinner1, spinner2,
//            spinner3, spinner4, checkBoxesPregunta5, checkBoxesPregunta9, resultadoTotal, aciertos) }
//
//    }

//    fun corregir(
//        grupo1: RadioGroup, grupo2: RadioGroup, grupo3: RadioGroup,
//        grupo4: RadioGroup, spinner1: Spinner, spinner2: Spinner, spinner3: Spinner,
//        spinner4: Spinner, checkBoxesPregunta5: List<CheckBox>,
//        checkBoxesPregunta9: List<CheckBox>, resultado: TextView, aciertos: TextView) {
//        respuestasMultiplesUsuario.clear()
//        respuestasSimpleUsuario[0] = recuperarRadioPulsado(grupo1)
//        respuestasSimpleUsuario[1] = spinner1.selectedItem.toString()
//        respuestasSimpleUsuario[2] = recuperarRadioPulsado(grupo2)
//        respuestasSimpleUsuario[3] = spinner2.selectedItem.toString()
//        respuestasSimpleUsuario[4] = spinner3.selectedItem.toString()
//        respuestasSimpleUsuario[5] = recuperarRadioPulsado(grupo3)
//        respuestasSimpleUsuario[6] = recuperarRadioPulsado(grupo4)
//        respuestasSimpleUsuario[7] = spinner4.selectedItem.toString()
//
//        val eleccionUserPreg5 = mutableListOf<String>()
//        for (checkbox in checkBoxesPregunta5) {
//            if (checkbox.isChecked) {
//                eleccionUserPreg5.add(checkbox.text.toString())
//            }
//        }
//        respuestasMultiplesUsuario.add(eleccionUserPreg5)
//
//        val eleccionUserPreg9 = mutableListOf<String>()
//        for (checkbox in checkBoxesPregunta9) {
//            if (checkbox.isChecked) {
//                eleccionUserPreg9.add(checkbox.text.toString())
//            }
//        }
//        respuestasMultiplesUsuario.add(eleccionUserPreg9)
//        val notaTotal = calcularNota()
//        resultado.setText(notaTotal.toString())
//        val aciertosTotal = cantidad.toString()
//        aciertos.setText(aciertosTotal)
//        cantidad = 0;
//    }
//
//    fun recuperarRadioPulsado(radioGroup: RadioGroup): String? {
//        val idRadoiElegido = radioGroup.checkedRadioButtonId
//         if (idRadoiElegido != -1) {
//            val radioElegidoTexto: RadioButton = findViewById(idRadoiElegido)
//             return radioElegidoTexto.text.toString()
//
//        } else {
//            return null
//        }
//    }
//
//    fun calcularNota(): Double {
//        var nota = 0.0
//
//        // Validar respuestas simples
//        for (i in respuestasCorrectasUnica.indices) {
//            if (respuestasSimpleUsuario[i] == respuestasCorrectasUnica[i]) {
//                nota++
//                cantidad++
//                indicadoresCorrectoUnica[i].setTextColor(resources.getColor(R.color.correcto))
//                indicadoresCorrectoUnica[i].setText("¡Correcto!")
//            } else {
//                indicadoresCorrectoUnica[i].setTextColor(resources.getColor(R.color.incorrecto))
//                indicadoresCorrectoUnica[i].setText("La respuesta correcta era " + respuestasCorrectasUnica[i])
//            }
//        }
//
//        for (i in respuestasCorrectasMultiopcion.indices) {
//            val respuestasCorrectas = respuestasCorrectasMultiopcion[i]
//
//            // Asegúrate de que no accedes a un índice fuera de rango
//            if (i < respuestasMultiplesUsuario.size) {
//                val respuestasUsuario = respuestasMultiplesUsuario[i]
//                var notaPregunta = 0.0
//
//                // Sumar puntos por respuestas correctas elegidas por el usuario
//                for (respuesta in respuestasCorrectas) {
//                    if (respuestasUsuario.contains(respuesta)) {
//                        cantidad++
//                        notaPregunta += 0.5 // Asignar puntaje por respuesta correcta
//                    }
//                }
//
//                // Restar puntos por respuestas incorrectas elegidas por el usuario
//                for (respuesta in respuestasUsuario) {
//                    if (!respuestasCorrectas.contains(respuesta)) {
//                        cantidad--
//                        notaPregunta -= 0.25 // Restar puntaje por respuesta incorrecta
//                    }
//                }
//
//                // Asegurarte que la nota no sea negativa
//                if (notaPregunta < 0) {
//                    notaPregunta = 0.0
//                }
//
//                // Acumular la nota total
//                nota = nota + notaPregunta
//
//                // Actualizar el indicador
//                if (notaPregunta == 1.0) {
//                    indicadoresCorrectoMultiple[i].setTextColor(resources.getColor(R.color.correcto))
//                    indicadoresCorrectoMultiple[i].text = "¡Correcto!"
//                } else if (notaPregunta < 1.0 && notaPregunta > 0.0) {
//                    indicadoresCorrectoMultiple[i].setTextColor(resources.getColor(R.color.incorrecto))
//                    indicadoresCorrectoMultiple[i].text = "¡Casi! Las respuestas correctas eran: " +
//                            "${respuestasCorrectas.joinToString(", ")}"
//                } else {
//                    indicadoresCorrectoMultiple[i].setTextColor(resources.getColor(R.color.incorrecto))
//                    indicadoresCorrectoMultiple[i].text =
//                        "Las respuestas correctas eran: ${respuestasCorrectas.joinToString(", ")}"
//                }
//            }
//        }
//        return nota
    }
}