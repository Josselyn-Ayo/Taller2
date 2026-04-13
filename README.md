# 🚗 Aplicación Distribuida de Registro de Vehículos

Sistema desarrollado para la gestión y registro de vehículos, integrando tecnologías modernas de persistencia en la nube y optimización de procesos mediante concurrencia.

---

##  Interfaz y Diseño

El diseño se basa en una estética técnica y minimalista, utilizando una paleta de colores profesional:

| Elemento | Color / Estilo |
| :--- | :--- |
| **Fondo Principal** | `#EDF2F7` (Gris Pálido / Beige frío) |
| **Acentos** | `#2D3748` (Gris Oscuro Profesional) |
| **Componentes** | Diseño limpio y funcional |

---

##  Características Principales

* **Gestión Integral:** Registro completo de marca, modelo, placa y año.
* **Fluidez de UI:** La interfaz se ejecuta en un **hilo (Thread)** independiente para evitar bloqueos y mejorar la respuesta del sistema.
* **Persistencia Cloud:** Integración directa con **MongoDB Atlas** para almacenamiento seguro en la nube.
* **Código Eficiente:** Uso de **Lombok** para reducir el código redundante y mejorar la legibilidad del modelo.

---

##  Stack Tecnológico

* **Java JDK:** Lenguaje de desarrollo principal.
* **JavaFX:** Framework para la creación de la interfaz gráfica de usuario (GUI).
* **MongoDB Atlas:** Base de datos NoSQL basada en documentos.
* **Lombok:** Librería para la generación automática de Getters, Setters y Constructores.
* **Threads:** Manejo de hilos para procesos asíncronos.
---
##  Arquitectura del Proyecto

El proyecto sigue una estructura modular para separar responsabilidades:
* **Conexión:** Clase encargada de gestionar el enlace con el clúster de MongoDB.
* **Hilos:** Implementación de concurrencia para la ejecución fluida de la ventana.
---
## Base de Datos

Se utiliza **MongoDB Atlas**. Los documentos dentro de la colección `vehiculos` siguen este esquema:


* **Modelo (`Vehiculo`):** Representación de los datos optimizada con anotaciones `@Data` de Lombok.
* **Vista (`FXML`):** Archivos de diseño para una interfaz gráfica moderna.
* **Controlador:** Lógica que procesa las entradas del usuario y coordina la aplicación.
```json
{
  "codigo": "String",
  "marca": "String",
  "modelo": "String",
  "precio": "double"
}

<img width="758" height="537" alt="image" src="https://github.com/user-attachments/assets/85847dc8-bdf1-4277-8168-0313d2f4de3f" />
