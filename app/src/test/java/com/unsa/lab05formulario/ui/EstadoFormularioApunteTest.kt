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
        estado.curso = "Otro curso"
        assertEquals(listOf("Otro título", "Otro curso", "6", "14", "Descripción"), textos(estado))
        estado.semana = "7"
        assertEquals(listOf("Otro título", "Otro curso", "7", "14", "Descripción"), textos(estado))
        estado.paginas = "20"
        assertEquals(listOf("Otro título", "Otro curso", "7", "20", "Descripción"), textos(estado))
        estado.descripcion = "Otra descripción"
        assertEquals(listOf("Otro título", "Otro curso", "7", "20", "Otra descripción"), textos(estado))
    }

    @Test
    fun corregirYVolverAIntroducirUnErrorActualizaLaValidacion() {
        val estado = EstadoFormularioApunte("Apunte", "IDNP", "abc", "14")
        assertFalse(estado.esValido)
        assertNotNull(estado.errores.semana)
        estado.semana = "6"
        assertTrue(estado.esValido)
        assertNull(estado.errores.semana)
        estado.titulo = " "
        assertFalse(estado.esValido)
        assertNotNull(estado.errores.titulo)
    }

    @Test
    fun erroresSoloSonVisiblesSiElCampoSeEditaOSeIntentaPublicar() {
        val estado = EstadoFormularioApunte() // Formulario inicialmente vacío
        // Al inicio ningún error es visible (campos limpios con placeholder)
        assertNull(estado.erroresVisibles.titulo)
        assertNull(estado.erroresVisibles.curso)
        assertNull(estado.erroresVisibles.semana)
        assertNull(estado.erroresVisibles.paginas)

        // Al editar Semana con un valor inválido, solo ese error se vuelve visible
        estado.semana = "abc"
        estado.marcarSemanaEditada()
        assertNull(estado.erroresVisibles.titulo)
        assertNotNull(estado.erroresVisibles.semana)

        // Al intentar publicar, se revelan los errores de todos los campos no completados
        estado.marcarIntentadoPublicar()
        assertNotNull(estado.erroresVisibles.titulo)
        assertNotNull(estado.erroresVisibles.curso)
        assertNotNull(estado.erroresVisibles.semana)
        assertNotNull(estado.erroresVisibles.paginas)
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
        restaurado.semana = "6"
        assertTrue(restaurado.esValido)
        assertEquals("abc", original.semana)
    }

    private fun textos(estado: EstadoFormularioApunte) = listOf(
        estado.titulo, estado.curso, estado.semana, estado.paginas, estado.descripcion,
    )
}
