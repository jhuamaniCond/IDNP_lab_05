package com.unsa.lab05formulario.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unsa.lab05formulario.ui.theme.Lab05FormularioTheme

/** Margen lateral de la pantalla (space-4 del sistema visual). */
private val Margen = 16.dp

/** Separación entre elementos, en vertical y en horizontal. */
private val Separacion = 12.dp

/**
 * Formulario «Subir apunte» de CampusDocs.
 *
 * Estructura (de arriba abajo, todo dentro de una Column):
 *   Encabezado
 *   "Datos del apunte":  Título del apunte               (ancho completo)
 *                        Curso                           (ancho completo)
 *                        Row[ Semana | N.º de páginas ]  (1 : 1)
 *   "Contenido":         Descripción (multilínea)        (ancho completo)
 *   Row[ Limpiar | Publicar ]                            (1 : 1)
 *
 * El composable no guarda ningún dato: recibe los cinco valores y avisa de
 * cada cambio y de cada botón mediante lambdas.
 */
@Composable
fun FormularioApunte(
    titulo: String,
    onTituloChange: (String) -> Unit,
    curso: String,
    onCursoChange: (String) -> Unit,
    semana: String,
    onSemanaChange: (String) -> Unit,
    paginas: String,
    onPaginasChange: (String) -> Unit,
    descripcion: String,
    onDescripcionChange: (String) -> Unit,
    onLimpiar: () -> Unit,
    onPublicar: () -> Unit,
    modifier: Modifier = Modifier,
    errores: ErroresApunte = ErroresApunte(),
    esValido: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Margen, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(Separacion),
    ) {
        Encabezado()

        TituloSeccion("Datos del apunte")
        CampoTexto(
            valor = titulo,
            onValueChange = onTituloChange,
            etiqueta = "Título del apunte",
            ejemplo = "Integración por partes",
            error = errores.titulo,
            modifier = Modifier.fillMaxWidth(),
            teclado = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next,
            ),
        )
        CampoTexto(
            valor = curso,
            onValueChange = onCursoChange,
            etiqueta = "Curso",
            ejemplo = "Cálculo II",
            error = errores.curso,
            modifier = Modifier.fillMaxWidth(),
            teclado = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next,
            ),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Separacion),
        ) {
            CampoTexto(
                valor = semana,
                onValueChange = onSemanaChange,
                etiqueta = "Semana",
                ejemplo = "6",
                error = errores.semana,
                modifier = Modifier.weight(1f),
                teclado = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            )
            CampoTexto(
                valor = paginas,
                onValueChange = onPaginasChange,
                etiqueta = "N.º de páginas",
                ejemplo = "14",
                error = errores.paginas,
                modifier = Modifier.weight(1f),
                teclado = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            )
        }

        TituloSeccion("Contenido")
        CampoTexto(
            valor = descripcion,
            onValueChange = onDescripcionChange,
            etiqueta = "Descripción",
            ejemplo = "Qué temas cubre el apunte y qué ejercicios incluye",
            modifier = Modifier.fillMaxWidth(),
            teclado = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Default,
            ),
            lineas = 4,
        )

        Spacer(Modifier.height(Separacion))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Separacion),
        ) {
            OutlinedButton(onClick = onLimpiar, modifier = Modifier.weight(1f)) {
                Text("Limpiar")
            }
            Button(
                onClick = onPublicar,
                enabled = esValido,
                modifier = Modifier.weight(1f),
            ) {
                Text("Publicar")
            }
        }
    }
}

@Composable
private fun Encabezado() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Subir apunte",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "Comparte tus apuntes de clase con tus compañeros de CampusDocs.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun TituloSeccion(texto: String) {
    Column(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(text = texto, style = MaterialTheme.typography.titleMedium)
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Preview(name = "Vacío", showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun FormularioVacioPreview() {
    Lab05FormularioTheme {
        Surface {
            FormularioApunte(
                titulo = "", onTituloChange = {},
                curso = "", onCursoChange = {},
                semana = "", onSemanaChange = {},
                paginas = "", onPaginasChange = {},
                descripcion = "", onDescripcionChange = {},
                onLimpiar = {}, onPublicar = {},
            )
        }
    }
}

@Preview(name = "Con datos", showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun FormularioConDatosPreview() {
    Lab05FormularioTheme {
        Surface {
            FormularioApunte(
                titulo = "Integración por partes", onTituloChange = {},
                curso = "Cálculo II", onCursoChange = {},
                semana = "6", onSemanaChange = {},
                paginas = "14", onPaginasChange = {},
                descripcion = "Fórmula, regla ILATE y doce ejercicios resueltos.",
                onDescripcionChange = {},
                onLimpiar = {}, onPublicar = {},
            )
        }
    }
}
