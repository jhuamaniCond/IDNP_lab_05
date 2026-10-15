package com.unsa.lab05formulario.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

/**
 * Campo de texto con etiqueta y texto de ejemplo. No guarda el valor: lo
 * recibe y avisa de cada cambio con [onValueChange], de modo que quien lo usa
 * decide dónde vive el dato. El ancho lo decide el contenedor a través de
 * [modifier] (fillMaxWidth en una Column, weight dentro de un Row).
 *
 * Con [lineas] mayor que 1 el campo se vuelve multilínea y arranca con esa
 * altura mínima.
 */
@Composable
fun CampoTexto(
    valor: String,
    onValueChange: (String) -> Unit,
    etiqueta: String,
    ejemplo: String,
    modifier: Modifier = Modifier,
    teclado: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    lineas: Int = 1,
    error: String? = null,
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(etiqueta) },
        placeholder = { Text(ejemplo) },
        singleLine = lineas == 1,
        minLines = lineas,
        keyboardOptions = teclado,
        isError = error != null,
        supportingText = error?.let { mensaje -> { Text(mensaje) } },
        modifier = modifier,
    )
}
