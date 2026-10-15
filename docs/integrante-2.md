# Aporte del integrante 2: estado y validación

## Texto para el informe

### 3. Definir estado independiente para los campos

Se implementó `EstadoFormularioApunte`, que contiene cinco propiedades independientes mediante `mutableStateOf`: título, curso, semana, número de páginas y descripción. Cada campo recibe su valor y actualiza únicamente su propiedad mediante su callback `onValueChange`. Así, escribir en un campo conserva la información de los demás y Compose puede actualizar la interfaz al cambiar el estado.

`PantallaSubirApunte` conserva este contenedor con `rememberSaveable` y un `listSaver` que guarda y restaura los cinco textos. Esto permite recuperar el borrador ante la recreación de la actividad, por ejemplo al rotar el dispositivo. También se conservan entradas temporalmente inválidas para que el usuario pueda corregirlas. Este mecanismo guarda estado de interfaz; no sustituye una base de datos ni implementa la publicación del apunte.

### 4. Implementar como mínimo dos reglas de validación

La función `validarApunte` comprueba los valores sin modificarlos y devuelve un objeto `ErroresApunte`. Cada propiedad contiene un mensaje de error o `null` si el campo cumple sus reglas. Se aplican dos tipos de reglas: obligatoriedad y formato/rango numérico.

| Campo | Regla | Ejemplo inválido |
|---|---|---|
| Título | Obligatorio; no acepta vacío ni solo espacios | `"   "` |
| Curso | Obligatorio; no acepta vacío ni solo espacios | `""` |
| Semana | Obligatoria; dígitos decimales y entero entre 1 y 2147483647 | `abc`, `0`, `-2`, `1.5` |
| N.º de páginas | Obligatorio; dígitos decimales y entero entre 1 y 2147483647 | `0`, `1,5`, `2147483648` |
| Descripción | Opcional | — |

Para semana y páginas se eliminan espacios exteriores únicamente durante la validación. Se usa `toIntOrNull` para evitar excepciones con valores demasiado grandes. El límite superior corresponde a la representación `Int`, no a un límite académico o funcional definido por el equipo. No se ha impuesto un máximo de semanas del curso porque no figura en los requisitos recibidos.

`estado.errores` calcula el resultado desde los valores actuales y `estado.esValido` indica si todos cumplen las reglas. No se guardan banderas duplicadas que puedan quedar desactualizadas. Por ejemplo, el formulario con título «Integración por partes», curso «Cálculo II», semana «6» y páginas «14» es válido aunque la descripción esté vacía.

## Integración con los otros integrantes

El integrante 3 puede obtener `estado.errores` en `PantallaSubirApunte`, pasar cada mensaje a `FormularioApunte` y después a `CampoTexto`. En este último puede configurar `isError` y `supportingText` del `OutlinedTextField`. Debe decidir cuándo mostrar los mensajes, por ejemplo al tocar un campo o intentar publicar, y usar `estado.esValido` para controlar la acción principal y comprobarla dentro del callback.

El integrante 4 puede conectar `onLimpiar` para asignar `""` a las cinco propiedades del estado, y conectar `onPublicar` con el comportamiento acordado por el equipo. Ambos callbacks siguen vacíos como en la versión inicial. Los componentes de diseño del integrante 1 se conservan.

## Verificación

Se agregaron pruebas JVM de obligatoriedad, formato numérico, límites, desbordamiento, independencia de campos, actualización de errores y guardado/restauración del contenedor. Se ejecutan con:

```powershell
./gradlew.bat :app:testDebugUnitTest
./gradlew.bat :app:assembleDebug
```

Los resultados efectivamente obtenidos se documentan en `VERIFICACION.md`.
La prueba del `Saver` comprueba la conversión y restauración del estado. Se
complementó con la prueba instrumentada descrita a continuación; no se probó
una rotación física del dispositivo.

Además, se ejecutó una prueba instrumentada de la pantalla real en Android 16
(API 36): completó los cinco campos, modificó el título y recreó la actividad.
Los cinco textos se conservaron. Las capturas antes y después y el diagrama de
estado y validación están en `docs/evidencias` y acompañan los puntos 3 y 4 del
informe grupal.

## Referencias

- Estado en Compose: https://developer.android.com/develop/ui/compose/state
- Guardado de estado: https://developer.android.com/develop/ui/compose/state-saving

## Nota sobre el informe grupal

Este texto cubre los puntos 3 y 4. Las conclusiones que afirman que ya se muestran errores, se bloquea Publicar o se limpian los campos deben respaldarse cuando los integrantes 3 y 4 implementen y prueben esos comportamientos.
