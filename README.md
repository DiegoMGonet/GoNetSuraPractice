# GoNetSuraPractice

## Proyecto de Ejemplo en Kotlin

### Contenido
Este proyecto está hecho 100% en Kotlin con la arquitectura MVVM, el objetivo de este proyecto es mostrar cómo se puede consumir un servicio dummy de la url: https://63d47d900e7ae91a009df5ab.mockapi.io/api/v1/
el cual regresa una lista de productos con los siguientes campos:
- id: Int
- name: String
- urlImage: String
- description: String
- price: Double
- weight: Double
- isFavorite: Boolean

Está hecho con corrutinas para los procesos en hilo (llamado a servicios), Retrofit para para ayudarnos a crear las funciones que nos regresará la información, y Koin para la inyección de dependencias