# 🐶 DogApp - Agenda de Citas para Mascotas

DogApp es una aplicación móvil desarrollada en Android Studio que permite a los administradores de veterinarias gestionar citas para mascotas de manera eficiente. Este proyecto fue desarrollado como parte del curso **Desarrollo de Aplicaciones para Dispositivos Móviles** en la **Universidad del Valle**.

## 📱 Características principales

- Login con autenticación biométrica por huella digital.
- Visualización de citas programadas.
- Registro de nuevas citas con integración de API para razas de perros.
- Vista detallada de cada cita, incluyendo síntomas, propietario y contacto.
- Edición y eliminación de citas almacenadas localmente.
- Interfaz atractiva y adaptativa basada en las Historias de Usuario.

## 🧱 Arquitectura del Proyecto

- Arquitectura **MVVM** con `Repository`.
- Base de datos local usando **Room** sobre SQLite.
- Consumo de APIs REST (https://dog.ceo/api).
- Uso de `Navigation` para gestión de vistas.
- Implementación de componentes como `AutoCompleteTextView`, `CardView`, `FloatingActionButton`, entre otros.

## 🔧 Tecnologías y Librerías Usadas

- **Kotlin** y **Android Studio**
- **Retrofit** para consumo de APIs
- **Room** para persistencia de datos
- **Lottie** para animaciones JSON
- **Git** y **GitHub** para control de versiones
- **Trello** para la gestión ágil del proyecto

## 🎯 Historias de Usuario Implementadas

1. **HU 1.0:** Login con huella digital.
2. **HU 2.0:** Home con lista de citas (Administrador).
3. **HU 3.0:** Crear nueva cita (Formulario y validaciones).
4. **HU 4.0:** Vista detallada de una cita.
5. **HU 5.0:** Edición de una cita.

## 👨‍💻 Integrantes - EQUIPO 15

| Código     | Nombre                          | Correo institucional                          |
|------------|----------------------------------|-----------------------------------------------|
| 2040798    | Juan Esteban Guerrero Camacho   | juan.esteban.guerrero@correounivalle.edu.co   |
| 1840786    | Julián Alexander Álvarez Payares| alvarez.julian@correounivalle.edu.co          |
| 2058558    | Néstor David Heredia Gutiérrez  | nestor.heredia@correounivalle.edu.co          |
| 1844360    | Deiby Santiago Muñoz Patiño     | deiby.munoz@correounivalle.edu.co             |
| 2110337    | Juan Diego Urriago Amesquita    | urriago.juan@correounivalle.edu.co            |
| 2222205    | Daniel Arias Castrillón         | daniel.arias.castrillon@correounivalle.edu.co |

## 🔗 Enlaces útiles

- 🔗 **Repositorio del proyecto:** [Enlace al repositorio](https://github.com/Anezeres/petsApplication) 
- 🔗 **Tablero de Trello:** [Enlace a Trello](https://trello.com/b/44ZcKHGT/redops-moviles) 

## 📅 Fechas Clave

- 🟢 Inicio del Sprint: 24 de abril de 2025
- 🛠️ Monitoría: 8 de mayo de 2025
- 🔴 Finalización del Sprint: 15 de mayo de 2025

---

Desarrollado con 💙 por el Equipo 15 — Universidad del Valle
