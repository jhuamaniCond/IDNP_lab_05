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
    tituloEditadoInicial: Boolean = tituloInicial.isNotEmpty(),
    cursoEditadoInicial: Boolean = cursoInicial.isNotEmpty(),
    semanaEditadaInicial: Boolean = semanaInicial.isNotEmpty(),
    paginasEditadaInicial: Boolean = paginasInicial.isNotEmpty(),
    intentadoPublicarInicial: Boolean = false,
) {
    var titulo by mutableStateOf(tituloInicial)
    var curso by mutableStateOf(cursoInicial)
    var semana by mutableStateOf(semanaInicial)
    var paginas by mutableStateOf(paginasInicial)
    var descripcion by mutableStateOf(descripcionInicial)

    var tituloEditado by mutableStateOf(tituloEditadoInicial)
    var cursoEditado by mutableStateOf(cursoEditadoInicial)
    var semanaEditada by mutableStateOf(semanaEditadaInicial)
    var paginasEditada by mutableStateOf(paginasEditadaInicial)
    var intentadoPublicar by mutableStateOf(intentadoPublicarInicial)

    // Se calculan desde los valores actuales para evitar errores desactualizados.
    val errores: ErroresApunte
        get() = validarApunte(titulo, curso, semana, paginas)

    /**
     * Errores visibles para la UI: solo se muestran en los campos que el usuario
     * ya ha editado/interactuado o si intentó publicar el formulario.
     */
    val erroresVisibles: ErroresApunte
        get() = ErroresApunte(
            titulo = if (tituloEditado || intentadoPublicar) errores.titulo else null,
            curso = if (cursoEditado || intentadoPublicar) errores.curso else null,
            semana = if (semanaEditada || intentadoPublicar) errores.semana else null,
            paginas = if (paginasEditada || intentadoPublicar) errores.paginas else null,
        )

    val esValido: Boolean
        get() = errores.esValido

    fun marcarTituloEditado() { tituloEditado = true }
    fun marcarCursoEditado() { cursoEditado = true }
    fun marcarSemanaEditada() { semanaEditada = true }
    fun marcarPaginasEditada() { paginasEditada = true }
    fun marcarIntentadoPublicar() { intentadoPublicar = true }

    companion object {
        /** Guarda los textos y banderas ante la recreación de la actividad. */
        val Saver = listSaver<EstadoFormularioApunte, String>(
            save = {
                listOf(
                    it.titulo,
                    it.curso,
                    it.semana,
                    it.paginas,
                    it.descripcion,
                    it.tituloEditado.toString(),
                    it.cursoEditado.toString(),
                    it.semanaEditada.toString(),
                    it.paginasEditada.toString(),
                    it.intentadoPublicar.toString(),
                )
            },
            restore = {
                EstadoFormularioApunte(
                    tituloInicial = it[0],
                    cursoInicial = it[1],
                    semanaInicial = it[2],
                    paginasInicial = it[3],
                    descripcionInicial = it[4],
                    tituloEditadoInicial = if (it.size > 5) it[5].toBoolean() else it[0].isNotEmpty(),
                    cursoEditadoInicial = if (it.size > 5) it[6].toBoolean() else it[1].isNotEmpty(),
                    semanaEditadaInicial = if (it.size > 5) it[7].toBoolean() else it[2].isNotEmpty(),
                    paginasEditadaInicial = if (it.size > 5) it[8].toBoolean() else it[3].isNotEmpty(),
                    intentadoPublicarInicial = if (it.size > 5) it[9].toBoolean() else false,
                )
            },
        )
    }
}
