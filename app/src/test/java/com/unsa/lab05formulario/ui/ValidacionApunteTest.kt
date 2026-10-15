package com.unsa.lab05formulario.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class ValidacionApunteTest {
    @Test
    fun formularioCompletoEsValidoYDescripcionNoEsRequerida() {
        assertTrue(validarApunte("Integración por partes", "Cálculo II", "6", "14").esValido)
    }

    @Test
    fun formularioVacioInformaCadaCampoObligatorio() {
        val errores = validarApunte("", "", "", "")
        assertFalse(errores.esValido)
        assertEquals("El título es obligatorio.", errores.titulo)
        assertEquals("El curso es obligatorio.", errores.curso)
        assertEquals("La semana es obligatoria.", errores.semana)
        assertEquals("El número de páginas es obligatorio.", errores.paginas)
    }

    @Test
    fun espaciosNoCumplenLosCamposObligatorios() {
        val errores = validarApunte(" \t", "\n ", "  ", "\t")
        assertNotNull(errores.titulo)
        assertNotNull(errores.curso)
        assertNotNull(errores.semana)
        assertNotNull(errores.paginas)
    }

    @Test
    fun unCampoIncorrectoNoGeneraErroresEnLosOtros() {
        val errores = validarApunte("Apunte", "IDNP", "abc", "14")
        assertFalse(errores.esValido)
        assertNull(errores.titulo)
        assertNull(errores.curso)
        assertNotNull(errores.semana)
        assertNull(errores.paginas)
    }
}

@RunWith(Parameterized::class)
class ValidacionNumericaTest(private val entrada: String, private val esperado: Boolean) {
    @Test
    fun semanaYPaginasAplicanLaMismaRegla() {
        val semana = validarApunte("Apunte", "IDNP", entrada, "1")
        val paginas = validarApunte("Apunte", "IDNP", "1", entrada)
        assertEquals("Semana: '$entrada'", esperado, semana.esValido)
        assertEquals("Páginas: '$entrada'", esperado, paginas.esValido)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "entrada={0}, valido={1}")
        fun casos(): Collection<Array<Any>> = listOf(
            arrayOf("1", true),
            arrayOf("6", true),
            arrayOf("14", true),
            arrayOf("001", true),
            arrayOf(" 14 ", true),
            arrayOf("2147483647", true),
            arrayOf("", false),
            arrayOf(" ", false),
            arrayOf("0", false),
            arrayOf("000", false),
            arrayOf("-2", false),
            arrayOf("+2", false),
            arrayOf("1.5", false),
            arrayOf("1,5", false),
            arrayOf("abc", false),
            arrayOf("1a", false),
            arrayOf("1 4", false),
            arrayOf("2147483648", false),
            arrayOf("999999999999999999999999999999", false),
        )
    }
}
