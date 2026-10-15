package com.unsa.lab05formulario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.unsa.lab05formulario.ui.FormularioApunte
import com.unsa.lab05formulario.ui.EstadoFormularioApunte
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
 * El estado independiente y las reglas de validación pertenecen al integrante 2.
 * estado.errores y estado.esValido quedan disponibles para la retroalimentación
 * y el bloqueo de Publicar (integrante 3). Los botones los conecta el integrante 4.
 */
@Composable
fun PantallaSubirApunte(modifier: Modifier = Modifier) {
    val estado = rememberSaveable(saver = EstadoFormularioApunte.Saver) {
        EstadoFormularioApunte()
    }

    FormularioApunte(
        titulo = estado.titulo,
        onTituloChange = {
            estado.titulo = it
            estado.marcarTituloEditado()
        },
        curso = estado.curso,
        onCursoChange = {
            estado.curso = it
            estado.marcarCursoEditado()
        },
        semana = estado.semana,
        onSemanaChange = {
            estado.semana = it
            estado.marcarSemanaEditada()
        },
        paginas = estado.paginas,
        onPaginasChange = {
            estado.paginas = it
            estado.marcarPaginasEditada()
        },
        descripcion = estado.descripcion,
        onDescripcionChange = { estado.descripcion = it },
        onLimpiar = {},
        onPublicar = { estado.marcarIntentadoPublicar() },
        modifier = modifier,
        errores = estado.erroresVisibles,
        esValido = estado.esValido,
    )
}
