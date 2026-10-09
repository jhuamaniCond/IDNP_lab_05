package com.unsa.lab05formulario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.unsa.lab05formulario.ui.FormularioApunte
import com.unsa.lab05formulario.ui.theme.Lab05FormularioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab05FormularioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { relleno ->
                    PantallaSubirApunte(modifier = Modifier.padding(relleno))
                }
            }
        }
    }
}

/**
 * Conecta el formulario con sus datos.
 *
 * PROVISIONAL: estado mínimo para poder escribir en los campos. La definición
 * del estado de los campos (punto 3), la validación (puntos 4 a 6) y el
 * comportamiento de Limpiar/Publicar (punto 7) se completan sobre este
 * composable.
 */
@Composable
fun PantallaSubirApunte(modifier: Modifier = Modifier) {
    var titulo by remember { mutableStateOf("") }
    var curso by remember { mutableStateOf("") }
    var semana by remember { mutableStateOf("") }
    var paginas by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    FormularioApunte(
        titulo = titulo, onTituloChange = { titulo = it },
        curso = curso, onCursoChange = { curso = it },
        semana = semana, onSemanaChange = { semana = it },
        paginas = paginas, onPaginasChange = { paginas = it },
        descripcion = descripcion, onDescripcionChange = { descripcion = it },
        onLimpiar = {},
        onPublicar = {},
        modifier = modifier,
    )
}
