# TiendaApp 🛍️ - Aplicación Móvil de Comercio Electrónico

Aplicación móvil desarrollada en **Kotlin** y **Jetpack Compose** para dispositivos Android, como parte del programa de formación en **Análisis y Desarrollo de Software (ADSO)** del **SENA**.

**Repositorio oficial en GitHub:** [https://github.com/Diego-Rojas19/android-actividad-4](https://github.com/Diego-Rojas19/android-actividad-4)

---

## 📌 1. Información del Proyecto

- **Nombre de la Aplicación:** TiendaApp
- **Institución:** Servicio Nacional de Aprendizaje (SENA)
- **Programa de Formación:** Análisis y Desarrollo de Software (ADSO)
- **Autor:** Diego Rojas
- **Lenguaje de Programación:** Kotlin
- **Toolkit de Interfaz de Usuario:** Jetpack Compose (Material 3)
- **Paquete Principal:** `com.example.tiendaapp`

---

## 🎯 2. Objetivo del Proyecto

Construir una aplicación móvil funcional e integrada de comercio electrónico que incorpore un flujo completo de autenticación de usuarios (Inicio de sesión, Registro y Recuperación de contraseña), catálogo interactivo de productos y carrito de compras interactivo, manteniendo una arquitectura limpia, segura y fiel a los patrones de diseño modernos basados en **Google Stitch** y **Material Design 3**.

---

## 🛠️ 3. Tecnologías Utilizadas

- **Lenguaje:** Kotlin 2.2.10
- **UI Framework:** Jetpack Compose con componentes de Material Design 3
- **Iconografía:** Material Icons Extended (`androidx.compose.material:material-icons-extended`)
- **Manejo de Estado y Navegación:** Estados reactivos de Compose (`remember`, `mutableStateOf`, `mutableStateListOf`, `rememberSaveable`) sin dependencias externas innecesarias.
- **Persistencia Local y Cifrado:** `SharedPreferences` con hashing **SHA-256** para el almacenamiento seguro de credenciales de usuario de prueba.
- **Construcción y Dependencias:** Gradle con Kotlin DSL (`build.gradle.kts`) y catálogo de versiones (`libs.versions.toml`).
- **Pruebas Unitarias:** JUnit 4 para la validación lógica de autenticación (`AuthLogicTest`).
- **Compatibilidad Android:** `minSdk` 24 (Android 7.0 Nougat) y `targetSdk` / `compileSdk` 37 (Android 15).

---

## 🚀 4. Funcionalidades Implementadas

### 🔐 A. Autenticación y Gestión de Usuarios
1. **Inicio de Sesión (`LoginScreen`)**:
   - Campo de correo electrónico con validación de sintaxis (`Patterns.EMAIL_ADDRESS`).
   - Campo de contraseña con opción para mostrar u ocultar el texto (`Visibility` / `VisibilityOff`).
   - Verificación estricta de credenciales mediante hashes **SHA-256**. Rechaza contraseñas incorrectas y correos no registrados.
   - Enlace directo a la recuperación de contraseña y al registro de nuevos usuarios.
   - Diálogo informativo al interactuar con el botón "Continuar con Google".

2. **Registro de Usuarios (`RegisterScreen`)**:
   - Basado en los componentes y colores institucionales de Google Stitch (`#008844`).
   - Campos para Nombre completo, Correo electrónico, Contraseña y Confirmación de contraseña.
   - Validación local de campos obligatorios, formato de correo, longitud mínima de 6 caracteres en la contraseña, coincidencia exacta entre contraseñas y aceptación obligatoria de los términos del ecosistema SENA.
   - Prevención de registros duplicados con correos ya existentes.

3. **Recuperación de Contraseña (`ForgotPasswordScreen`)**:
   - Diseño adaptable exportado de Google Stitch con emblema de seguridad SENA.
   - Verificación previa del correo electrónico en el almacenamiento de la aplicación.
   - Diálogo informativo de restablecimiento de contraseña local y aclaración técnica sobre servidores de correo externos.

### 🛍️ B. Catálogo y Comercio Electrónico
4. **Catálogo de Productos (`HomeScreen`)**:
   - Lista interactiva de productos de muestra con nombre, descripción, precio formateado (`$%,d`) e imagen/emoji representativo.
   - Botón para agregar productos al carrito con notificación contextual (`Toast`).
   - Indicador dinámico (`BadgedBox`) en el botón del carrito que muestra la cantidad en tiempo real de artículos seleccionados.
   - Botón superior de cierre de sesión seguro.

5. **Carrito de Compras (`CartScreen`)**:
   - Lista detallada de productos añadidos con opción de eliminación individual.
   - Cálculo automático del total a pagar en pesos colombianos.
   - Pantalla de estado vacío cuando no hay artículos, con botón directo para regresar al catálogo.
   - Botón "Finalizar compra" que confirma la transacción y limpia el carrito.

---

## 💻 5. Requisitos del Sistema

Para compilar y ejecutar el proyecto localmente se requiere:

- **Android Studio:** Ladybug (2024.2.1) o superior.
- **Java Development Kit (JDK):** Versión 11 o superior.
- **Android SDK:** API Level 37 o superior instalado.
- **Dispositivo de Prueba:** Emulador Android (AVD) con Android 7.0 (API 24) o posterior, o un dispositivo físico conectado vía depuración USB.

---

## 📑 6. Instrucciones para Abrir y Ejecutar el Proyecto

1. **Clonar el Repositorio de GitHub:**
   ```bash
   git clone https://github.com/Diego-Rojas19/android-actividad-4.git
   ```
2. **Abrir en Android Studio:**
   - Inicia Android Studio.
   - Haz clic en **File > Open...** (o **Open an Existing Project**).
   - Navega hasta la carpeta raíz donde clonaste el proyecto (`TiendaApp`) y selecciona **OK**.

3. **Sincronizar dependencias con Gradle:**
   - Android Studio iniciará la sincronización automáticamente.
   - Si no inicia o se solicita manualmente, haz clic en **Sync Project with Gradle Files** (icono del elefante en la barra superior).

4. **Ejecutar la Aplicación:**
   - Selecciona el módulo `:app` en la barra de configuración de ejecución.
   - Elige tu emulador Android Virtual Device (AVD) o dispositivo físico conectado.
   - Presiona el botón **Run 'app'** (icono verde ▶️ o la combinación de teclas `Shift + F10`).

---

## 📂 7. Estructura de Carpetas y Archivos Principales

```text
TiendaApp/
├── app/
│   ├── build.gradle.kts                   # Configuración del módulo app
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml        # Manifiesto de la aplicación
│       │   └── java/com/example/tiendaapp/
│       │       ├── MainActivity.kt        # Actividad principal y enrutador de navegación
│       │       ├── data/
│       │       │   └── UserRepository.kt  # Gestión local de usuarios y hash SHA-256
│       │       └── ui/
│       │           ├── screen/
│       │           │   ├── LoginScreen.kt          # Pantalla de Inicio de Sesión
│       │           │   ├── RegisterScreen.kt       # Pantalla de Registro (Diseño Stitch)
│       │           │   ├── ForgotPasswordScreen.kt # Pantalla de Recuperación (Diseño Stitch)
│       │           │   ├── HomeScreen.kt           # Catálogo de Productos
│       │           │   ├── CartScreen.kt           # Carrito de Compras
│       │           │   └── Product.kt              # Modelo de datos de Producto y muestra
│       │           └── theme/                      # Tema de Compose (Color, Theme, Type)
│       └── test/
│           └── java/com/example/tiendaapp/
│               └── AuthLogicTest.kt       # Pruebas unitarias de la lógica de autenticación
├── gradle/
│   └── libs.versions.toml                 # Catálogo centralizado de versiones
├── build.gradle.kts                       # Configuración Gradle raíz
├── README.md                              # Documentación general del proyecto
└── settings.gradle.kts                    # Configuración de módulos del proyecto
```

---

## 🔑 8. Correos de Ejemplo y Credenciales de Prueba

### 🔐 A. Credenciales de Demostración Pre-configuradas
Las siguientes cuentas se encuentran configuradas en `UserRepository.kt` para probar el inicio de sesión directo sin necesidad de registrarse previamente:

| Usuario | Correo Electrónico | Contraseña |
| :--- | :--- | :--- |
| **Aprendiz SENA** | `admin@sena.edu.co` | `sena1234` |
| **Usuario Ejemplo** | `usuario@correo.com` | `123456` |

### 📧 B. Correos de Ejemplo para Pruebas de Registro
Los siguientes correos son ejemplos recomendados para probar el formulario de creación de cuentas nuevas en la pantalla de **Registro** (no están pre-registrados con contraseña por defecto, lo que permite probar el flujo de alta de nuevos usuarios):

- `diego.prueba@gmail.com`
- `usuario123@correo.com`
- `aprendiz@sena.edu.co`

---

## 🧪 9. Pruebas Unitarias y Automatizadas

El proyecto cuenta con una suite de pruebas unitarias implementada en [`AuthLogicTest.kt`](file:///C:/Users/Sena/AndroidStudioProjects/TiendaApp/app/src/test/java/com/example/tiendaapp/AuthLogicTest.kt) para validar la lógica de negocio sin depender de la interfaz gráfica.

### Pruebas que se ejecutan:
- Verificación de cuentas pre-registradas por defecto.
- Validación de inicio de sesión exitoso con credenciales válidas.
- Rechazo de inicio de sesión con contraseña incorrecta.
- Rechazo de inicio de sesión con correo no registrado.
- Registro exitoso de nuevos usuarios y posterior autenticación con los nuevos datos.
- Prevención de registros duplicados con el mismo correo.
- Restablecimiento de contraseña y desactivación de la clave anterior.
- Rechazo de recuperación para correos inexistentes.

### Cómo ejecutar las pruebas:
Desde la terminal del proyecto en Android Studio:
```powershell
./gradlew.bat app:testDebugUnitTest
```
O haciendo clic derecho sobre el archivo `AuthLogicTest.kt` dentro de Android Studio y seleccionando **Run 'AuthLogicTest'**.

---

## ⚠️ 10. Limitaciones Conocidas y Configuraciones Adicionales

1. **Inicio de Sesión con Google**:
   - *Estado actual:* Al presionar el botón "Continuar con Google", la app muestra un diálogo explicativo de la falta de configuración.
   - *Requerimiento:* Para habilitar Google Sign-In real se necesita incluir la biblioteca `com.google.android.gms:play-services-auth` o Android Credential Manager y vincular el archivo de configuración `google-services.json` desde Firebase / Google Cloud Console.

2. **Envío de Correos Reales de Recuperación**:
   - *Estado actual:* La recuperación valida la existencia del usuario en la base de datos local y permite actualizar la clave localmente.
   - *Requerimiento:* Para enviar un enlace de restablecimiento real a casillas de correo externas (Gmail, Outlook, etc.) se requiere integrar un proveedor de autenticación en la nube como **Firebase Authentication** o conectar un servidor SMTP.

---

## 🔗 11. Enlace al Repositorio

El código fuente del proyecto se encuentra alojado en GitHub:
👉 **[https://github.com/Diego-Rojas19/android-actividad-4](https://github.com/Diego-Rojas19/android-actividad-4)**

---

## 👨‍💻 12. Autor y Créditos Académicos

- **Autor:** Diego Rojas
- **Programa de Formación:** Análisis y Desarrollo de Software (ADSO)
- **Institución:** Servicio Nacional de Aprendizaje (SENA)
