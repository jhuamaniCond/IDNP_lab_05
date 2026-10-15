# Verificación del aporte del integrante 2

Fecha: 15 de octubre de 2026 (America/Lima).

Base: rama `main`, commit `89d62d19bbcf65601c5e480cd0ffb52ebf7ea0c9`.
El proyecto se clonó directamente desde GitHub en `D:/Jhast/IDNP_lab_05_GitHub`.

## Resultados

| Comprobación | Resultado |
|---|---|
| `:app:assembleDebug` | APK generado correctamente |
| `:app:testDebugUnitTest` | 26 pruebas aprobadas; 0 fallos, errores u omisiones |
| `:app:connectedDebugAndroidTest` | 1 prueba aprobada en Android 16, API 36 |
| `:app:lintDebug` | 0 errores; 6 advertencias preexistentes de dependencias e icono |
| `git diff --check` | Sin errores de espacios |

Las pruebas JVM comprueban los campos obligatorios, el aislamiento de errores,
19 entradas numéricas aplicadas tanto a semana como a páginas, la independencia
de los cinco estados, la actualización de la validación y el `Saver`.

La prueba instrumentada `EstadoFormularioPantallaTest` completa los cinco campos
de la pantalla real, cambia el título, comprueba que los otros valores se conservan,
recrea la actividad con `ActivityScenario.recreate()` y vuelve a comprobar los cinco
textos. Genera capturas antes y después de la recreación. Se repitió directamente
con AndroidJUnitRunner para recuperar estas imágenes después de la limpieza que
hace la ejecución de Gradle; también terminó con `OK (1 test)`.

No se afirma haber probado una rotación física: la comprobación realizada fue
la recreación real de la actividad. El emulador usa el perfil Pixel 6.

## Evidencias

- [Antes de recrear](evidencias/estado-antes.png).
- [Estado restaurado](evidencias/estado-restaurado.png).
- [Comparación para el informe](evidencias/comparacion-restauracion.png): recorte
  del área del formulario de las capturas originales, sin alterar los datos.
- [Diagrama del flujo de estado y validación](evidencias/flujo-estado-validacion.png).
- [Fuente SVG del diagrama](evidencias/flujo-estado-validacion.svg).

Los resultados completos se generan en `app/build/test-results/testDebugUnitTest`,
`app/build/outputs/androidTest-results/connected` y `app/build/reports`.
Estos archivos de compilación no se versionan.

## Reproducir

Con Java y el SDK configurados, ejecutar desde la raíz del proyecto:

```powershell
./gradlew.bat :app:testDebugUnitTest :app:assembleDebug :app:lintDebug
./gradlew.bat :app:connectedDebugAndroidTest
```

La segunda orden necesita un dispositivo o emulador conectado. En este equipo,
el JDK, el SDK, el emulador y la caché de Gradle están en `D:/Codex/IDNP_lab_05`.
`local.properties` no se versiona porque depende del equipo.

## Informe e integración

El texto de los puntos 3 y 4 se conserva en [integrante-2.md](integrante-2.md).
Las evidencias corresponden a la implementación de este repositorio y se insertaron
en esos puntos del [informe grupal](https://docs.google.com/document/d/19rDguSgE6kvwSyeXbsgz9AhGzCLqhPrY/edit).
Se verificaron visualmente las ocho páginas y se comprobó que el archivo guardado
en Drive coincide byte por byte con la versión revisada. Se conservaron todos
los textos, las imágenes y las demás partes del archivo original.

El alcance de este aporte son los puntos 3 y 4. `estado.errores` y `estado.esValido`
quedan disponibles para que el integrante 3 conecte los mensajes visibles y la
restricción de Publicar. El comportamiento de Limpiar/Publicar y las pruebas
completas de esas acciones corresponden al integrante 4.
