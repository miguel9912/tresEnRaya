package com.example.tresenrayados

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tablero = Array(3) { IntArray(3) }
    private var jugadorActual = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val txtSalida = findViewById<EditText>(R.id.txtSalida)


                    val casilla = findViewById<ImageView>(
                        resources.getIdentifier(
                            "casilla${i}${j}",
                            "id",
                            packageName
                        )

                    )
                    casilla.setOnClickListener { onCasillaClick(casilla, i, j, tablero)
                        comprobar(tablero)}


            }

        }
        /*for (i in 0 until 3) {
            for (j in 0 until 3) {
                txtSalida.setText(tablero[i][j].toString())
            }}*/
    }

    private fun comprobar(tablero: Array<IntArray>): Boolean {
        var cuentaFilas = 0
        var cuentaColumnas = 0
        var cuentaDiagonalP = 0
        var cuentaDiagonalS = 0
        var salida = false

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                cuentaFilas += tablero[i][j]
                cuentaColumnas += tablero[j][i]
                if(i == j){
                    cuentaDiagonalP += tablero[i][j]
                }
                if(i+j == tablero.size -1) {
                    cuentaDiagonalS += tablero[i][j]
                }
            }
            if(cuentaFilas == 6 || cuentaFilas == 15 || cuentaColumnas == 6 || cuentaColumnas == 15){
                salida= true
                Toast.makeText(this, "Tomaa", Toast.LENGTH_SHORT).show()
            }
            cuentaColumnas = 0
            cuentaFilas = 0
        }
        if(cuentaDiagonalP == 6 || cuentaDiagonalP == 15 || cuentaDiagonalS == 6 || cuentaDiagonalS == 15){
            salida= true
        }
        return salida
    }
    private fun onCasillaClick(view: ImageView, fila: Int, columna: Int, tablero: Array<IntArray> ) {
        val casilla = view

        if (tablero[fila][columna] == 0) {
            if (jugadorActual == 1) {
                casilla.setImageResource(R.drawable.equis)
                tablero[fila][columna] = 2
            } else {
                casilla.setImageResource(R.drawable.circulo)
                tablero[fila][columna] = 5
            }

            jugadorActual = if (jugadorActual == 1) 2 else 1
        }
    }
}