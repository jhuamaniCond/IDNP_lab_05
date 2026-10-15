package com.unsa.lab05formulario

import android.graphics.Bitmap
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Verifica el enlace real de la UI y rememberSaveable al recrear la actividad. */
@RunWith(AndroidJUnit4::class)
class EstadoFormularioPantallaTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun camposIndependientesSeConservanAlRecrearLaActividad() {
        val datos = linkedMapOf(
            "Título del apunte" to "Apunte de IDNP",
            "Curso" to "IDNP",
            "Semana" to "6",
            "N.º de páginas" to "14",
            "Descripción" to "Estado y validacion del formulario.",
        )
        datos.forEach { (etiqueta, valor) ->
            compose.onNode(hasSetTextAction() and hasText(etiqueta))
                .performScrollTo().performTextReplacement(valor)
        }
        // Cambiar el título no debe sobrescribir los otros cuatro campos.
        compose.onNode(hasSetTextAction() and hasText("Título del apunte"))
            .performScrollTo().performTextReplacement("Borrador de IDNP")
        datos["Título del apunte"] = "Borrador de IDNP"
        comprobar(datos)
        captura("estado-antes.png")

        compose.activityRule.scenario.recreate()
        compose.waitForIdle()
        comprobar(datos)
        captura("estado-restaurado.png")
    }

    private fun comprobar(datos: Map<String, String>) {
        datos.forEach { (etiqueta, valor) ->
            compose.onNode(hasSetTextAction() and hasText(etiqueta))
                .performScrollTo().assertTextContains(valor)
        }
        compose.onNode(hasSetTextAction() and hasText("Título del apunte"))
            .performScrollTo()
        compose.waitForIdle()
    }

    private fun captura(nombre: String) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val archivo = File(instrumentation.targetContext.getExternalFilesDir(null), nombre)
        val bitmap = requireNotNull(instrumentation.uiAutomation.takeScreenshot())
        archivo.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
    }
}
