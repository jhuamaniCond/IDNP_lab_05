package com.unsa.lab05formulario.ui

/** null indica que el campo cumple sus reglas. La descripción es opcional. */
data class ErroresApunte(
    val titulo: String? = null,
    val curso: String? = null,
    val semana: String? = null,
    val paginas: String? = null,
) {
    val esValido: Boolean
        get() = titulo == null && curso == null && semana == null && paginas == null
}

/** Reglas puras: no modifican el texto ni deciden cuándo mostrar los errores. */
fun validarApunte(
    titulo: String,
    curso: String,
    semana: String,
    paginas: String,
): ErroresApunte = ErroresApunte(
    titulo = if (titulo.isBlank()) "El título es obligatorio." else null,
    curso = if (curso.isBlank()) "El curso es obligatorio." else null,
    semana = validarEnteroPositivo(semana, "La semana es obligatoria."),
    paginas = validarEnteroPositivo(paginas, "El número de páginas es obligatorio."),
)

private fun validarEnteroPositivo(valor: String, mensajeObligatorio: String): String? {
    val texto = valor.trim()
    if (texto.isEmpty()) return mensajeObligatorio
    if (texto.any { it !in '0'..'9' }) return "Ingresa un número entero mayor que cero."
    val numero = texto.toIntOrNull()
    return if (numero == null || numero <= 0) {
        "Ingresa un número entero entre 1 y ${Int.MAX_VALUE}."
    } else {
        null
    }
}
