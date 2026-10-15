# Lab05Formulario — CampusDocs: Subir apunte

Guía de Práctica N° 05 · Formulario con Row, Column y validación de estado
(Introducción al Desarrollo de Nuevas Plataformas, UNSA 2026-B).

Pantalla «Subir apunte» de CampusDocs: título, curso, semana, número de
páginas y descripción del apunte, con los botones Limpiar y Publicar.

- Kotlin + Jetpack Compose + Material 3 (`compileSdk` 37, `minSdk` 24).
- Abrir con Android Studio y ejecutar la configuración `app`.

## Integrante 2: estado y validación

Los cinco campos tienen estado independiente, conservado con `rememberSaveable`.
Título y curso son obligatorios; semana y páginas deben ser enteros positivos.
Descripción es opcional. `estado.errores` y `estado.esValido` están disponibles
para conectar la retroalimentación y el bloqueo de la acción principal.

El texto para los puntos 3 y 4 del informe y las indicaciones de integración
están en [docs/integrante-2.md](docs/integrante-2.md).

Verificar con `./gradlew.bat :app:testDebugUnitTest :app:assembleDebug`.
Con un dispositivo o emulador conectado, ejecutar
`./gradlew.bat :app:connectedDebugAndroidTest` para comprobar la independencia
de los campos y su restauración al recrear la actividad.
Los resultados y las capturas están descritos en [docs/VERIFICACION.md](docs/VERIFICACION.md).
