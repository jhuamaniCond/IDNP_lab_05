package com.unsa.lab05formulario.ui

import androidx.compose.runtime.saveable.SaverScope
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EstadoFormularioApunteTest {
    @Test
    fun editarUnCampoConservaLosOtrosCuatro() {
        val estado = EstadoFormularioApunte("Título", "Curso", "6", "14", "Descripción")
        estado.titulo = "Otro título"
        assertEquals(listOf("Otro título", "Curso", "6", "14", "Descripción"), textos(estado))
    }

    @Test
    fun erroresSoloSonVisiblesSiElCampoSeEditaOSeIntentaPublicar() {
        val estado = EstadoFormularioApunte()
        assertNull(estado.erroresVisibles.titulo)

        estado.semana = "abc"
        estado.marcarSemanaEditada()
        assertNull(estado.erroresVisibles.titulo)
        assertNotNull(estado.erroresVisibles.semana)

        estado.marcarIntentadoPublicar()
        assertNotNull(estado.erroresVisibles.titulo)
    }

    @Test
    fun limpiarRestableceCamposYEstadosDeEdicion() {
        val estado = EstadoFormularioApunte("Título", "Curso", "6", "14", "Descripción")
        estado.marcarIntentadoPublicar()
        estado.limpiar()

        assertEquals(listOf("", "", "", "", ""), textos(estado))
        assertFalse(estado.tituloEditado)
        assertFalse(estado.intentadoPublicar)
        assertNull(estado.erroresVisibles.titulo)
    }

    @Test
    fun saverRestauraLosCincoTextosInclusoUnBorradorInvalido() {
        val original = EstadoFormularioApunte("Apunte", "IDNP", "abc", "14", "Borrador")
        val scope = object : SaverScope {
            override fun canBeSaved(value: Any): Boolean = value is String
        }
        val guardado = with(EstadoFormularioApunte.Saver) { scope.save(original) }
        val restaurado = EstadoFormularioApunte.Saver.restore(requireNotNull(guardado))!!
        assertEquals(textos(original), textos(restaurado))
        assertFalse(restaurado.esValido)
    }

    private fun textos(estado: EstadoFormularioApunte) = listOf(
        estado.titulo, estado.curso, estado.semana, estado.paginas, estado.descripcion
    )
}