package com.unsa.lab05formulario.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

/** Cinco estados independientes; editar un campo no modifica los demás. */
class EstadoFormularioApunte(
    tituloInicial: String = "",
    cursoInicial: String = "",
    semanaInicial: String = "",
    paginasInicial: String = "",
    descripcionInicial: String = "",
) {
    var titulo by mutableStateOf(tituloInicial)
    var curso by mutableStateOf(cursoInicial)
    var semana by mutableStateOf(semanaInicial)
    var paginas by mutableStateOf(paginasInicial)
    var descripcion by mutableStateOf(descripcionInicial)

    // Se calculan desde los valores actuales para evitar errores desactualizados.
    val errores: ErroresApunte
        get() = validarApunte(titulo, curso, semana, paginas)

    val esValido: Boolean
        get() = errores.esValido

    companion object {
        /** Guarda los textos ante la recreación de la actividad; no es persistencia. */
        val Saver = listSaver<EstadoFormularioApunte, String>(
            save = { listOf(it.titulo, it.curso, it.semana, it.paginas, it.descripcion) },
            restore = { EstadoFormularioApunte(it[0], it[1], it[2], it[3], it[4]) },
        )
    }
}
