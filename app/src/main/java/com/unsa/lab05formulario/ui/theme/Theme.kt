package com.unsa.lab05formulario.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Paleta del sistema visual de CampusDocs (Primario, Neutros y Feedback). */
private val Esquema = lightColorScheme(
    primary = Color(0xFF2563EB),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDBEAFE),
    onPrimaryContainer = Color(0xFF1D4ED8),
    background = Color(0xFFF9FAFB),
    onBackground = Color(0xFF1F2937),
    surface = Color(0xFFF9FAFB),
    onSurface = Color(0xFF1F2937),
    onSurfaceVariant = Color(0xFF6B7280),
    outline = Color(0xFF6B7280),
    outlineVariant = Color(0xFFE5E7EB),
    error = Color(0xFFDC2626),
)

/**
 * Tema mínimo de la app. Se fija el esquema claro (sin color dinámico) para
 * que las capturas no dependan del fondo de pantalla del emulador.
 */
@Composable
fun Lab05FormularioTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Esquema, content = content)
}
