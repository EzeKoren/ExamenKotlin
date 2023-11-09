# Examen Kotlin

Desarrollo Parcial TP3 ORT Argentina iniciado el 2/11/2023.
Fecha de entrega  9/11/2023 - 19:00 Hs.

## Introducción

Bienvenido al desarrollo parcial del Trabajo Práctico 3 (TP3) de nuestra aplicación para la adopción de mascotas en el sistema operativo Android, utilizando el lenguaje de programación Kotlin y aprovechando la API pública de [Dog API](https://dog.ceo/dog-api/documentation/random) para obtener información sobre perros, incluyendo fotos, razas y subrazas.

En esta versión de la aplicación, nos enfocaremos en la adopción de perros. La aplicación cuenta con diversas funcionalidades, desde un listado de perros disponibles para adopción hasta la posibilidad de crear nuevas publicaciones de adopción y gestionar favoritos. Además, hemos incorporado un diseño intuitivo y fácil de usar, priorizando la experiencia del usuario.

## Requisitos y Funcionalidades

La aplicación cuenta con un TabBar que organiza las secciones principales y un BottomBar que permite la navegación entre las distintas funcionalidades. En el TabBar, se encuentra el menú desplegable de perfil y configuración, que incluye opciones para personalizar la experiencia del usuario, como el modo claro u oscuro de la aplicación. El BottomBar facilita el acceso a las secciones principales de la aplicación, incluyendo el Home (listado de perros ), la sección de Favoritos, la lista de perros en adopción y la sección para crear nuevas publicaciones de adopción. La sección de Configuración también permite al usuario personalizar su experiencia, incluyendo el modo claro u oscuro de la aplicación.

Filtros y Búsqueda Avanzada: La aplicación ofrece filtros para buscar perros por raza, ubicación, y posibilidad de ordenar por fecha de publicación. Además, cuenta con una barra de búsqueda que sugiere los perros a medida que el usuario escribe, mejorando la precisión de las búsquedas y reduciendo errores.

Detalles del Perro: Al seleccionar un perro en la lista, los usuarios pueden acceder a información detallada, incluyendo raza, subraza (si aplica), nombre, edad, género, descripción, peso, ubicación e imagenre presentativa del perro. También se proporciona información del cuidador/dueño y un botón de llamada directa.

Gestión de Favoritos y Adopciones: Los usuarios tienen la posibilidad de agregar o quitar perros de su lista de favoritos directamente desde la vista detallada. Además, pueden adoptar un perro, lo que implica  actualización en el estado de adopción del perro.

Consideraciones de Diseño y Técnicas
Paleta de Colores Personalizable: La aplicación permite a los usuarios elegir entre el modo claro y oscuro. La paleta de colores ha sido elegida cuidadosamente para ofrecer una experiencia visual agradable.

Buenas Prácticas de Diseño: El diseño de la interfaz ha sido desarrollado siguiendo buenas prácticas y guidelines para asegurar una experiencia de usuario consistente y fácil de entender.

Arquitectura y Tecnologías Utilizadas: Para el desarrollo de la aplicación, hemos empleado una arquitectura MVVM (Modelo-Vista-ViewModel) que ofrece una estructura organizada y mantenible. Además, hemos utilizado ViewModel, autolayout, independencia de componentes, y código expresivo y declarativo para asegurar la robustez y prolijidad del proyecto.

Futuras Expansiones y Mejoras
En caso de extender la aplicación para incluir otras mascotas, como gatos, la aplicación está diseñada de forma flexible para adaptarse a futuras incorporaciones. Para lograr esto, se podrían realizar modificaciones específicas en las secciones relacionadas con las características particulares de cada tipo de mascota, manteniendo la estructura general de la aplicación.

Además, en busca de mejoras continuas, estamos abiertos a sugerencias y feedback por parte de los usuarios y evaluadores. Nos interesa recibir comentarios sobre la experiencia de usuario, el rendimiento de la aplicación y cualquier otra área que se pueda optimizar para ofrecer una aplicación aún más sólida y satisfactoria.

Casos de Prueba y Evaluación
Durante el proceso de evaluación, se han considerado varios casos de prueba para garantizar la calidad y funcionamiento adecuado de la aplicación. Algunos de estos casos incluyen probar la aplicación en modo oscuro, publicar mascotas en adopción, scrollear el listado con al menos 10 publicaciones cargadas, adoptar un perro, filtrar búsquedas por raza, subraza, ubicación, edad y género, así como también gestionar la lista de favoritos.

### Requisitos Previos

- Android Studio instalado en tu sistema.
- Conexión a internet para descargar las dependencias del proyecto.

### Pasos

1. Clonar el Repositorio
    - Abre una terminal o línea de comandos y ejecuta el siguiente comando para clonar el repositorio: `git clone https://github.com/EzeKoren/ExamenKotlin.git`

2. Abrir el Proyecto en Android Studio
    - Selecciona File en el menú y luego Open.
    - Navega hasta el directorio donde clonaste el repositorio y selecciona la carpeta del proyecto (tu_proyecto).

3. Configuración de Dependencias
    - Android Studio debería detectar automáticamente las dependencias del proyecto y descargarlas si es necesario. Si hay algún problema, asegúrate de tener una conexión a internet activa y verifica la configuración de tu archivo build.gradle.

4. Conectar un Dispositivo o Emulador
    - Conecta un dispositivo Android a tu computadora a través de USB o crea y configura un emulador desde Android Studio.

5. Ejecutar el Proyecto
    - Selecciona el dispositivo o emulador que deseas usar desde la barra de herramientas.
    - Haz clic en el botón "Run"  para iniciar la aplicación.

## Guía de uso

1. Presione el boton "Get started" para acceder al login.

<p align="center">
  <img src="https://raw.githubusercontent.com/EzeKoren/ExamenKotlin/master/app/src/main/res/drawable/readme_init_page.png" alt="readme_init_page.png" width="200" height="400">
</p>

1. Ingrese su nombre de usuario y luego presione el boton "login"

<p align="center">
  <img src="https://raw.githubusercontent.com/EzeKoren/ExamenKotlin/master/app/src/main/res/drawable/readme_login_page.png" alt="readme_init_page.png" width="200" height="400">
</p>

### Una vez Logueado

- Seleccione un perro para ver su perfil  

<p align="center">
  <img src="https://raw.githubusercontent.com/EzeKoren/ExamenKotlin/master/app/src/main/res/drawable/readme_home_page.png" alt="readme_init_page.png" width="200" height="400">
</p>

- Genere una publicación de adopción

<p align="center">
  <img src="https://raw.githubusercontent.com/EzeKoren/ExamenKotlin/master/app/src/main/res/drawable/readme_form_page.png" alt="readme_init_page.png" width="200" height="400">
</p>

## Contribución

- Paula Fuentes
- Mariano Di Gennaro
- Ezequiel Korelblum
- Uriel Swarcman
- Javier Bagdadi
- Federico Peirano
- Facundo Lopez Bruno

## Información de contacto

- paulyta1983@gmail.com
- mariano.psico@gmail.com
- ezequiel@losko.com.ar
- urielszw@gmail.com
- javibagdadi@hotmail.com
- fedepr2345@gmail.com
- faculopez93@hotmail.com.ar

## Licencia

MIT License

Copyright (c) [2023] Paula Fuentes, Mariano Di Gennaro, Ezequiel Korelblum, Uriel Swarcman, Javier Bagdadi, Federico Peirano, Facundo Lopez Bruno

Se concede permiso, de forma gratuita, a cualquier persona que obtenga una copia de este software y los archivos de documentación asociados (el "Software"), para tratar el Software sin restricciones, incluidos, entre otros, los derechos de uso, copia, modificación, fusión, publicación, distribución, sublicencia y/o venta de copias del Software, y para permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:

El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.

EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUIDAS, ENTRE OTRAS, LAS GARANTÍAS DE COMERCIABILIDAD, IDONEIDAD PARA UN FIN PARTICULAR Y NO INFRACCIÓN. EN NINGÚN CASO LOS AUTORES O TITULARES DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES POR NINGUNA RECLAMACIÓN, DAÑO U OTRA RESPONSABILIDAD, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O DE OTRA MANERA, QUE SURJA DE, O EN CONEXIÓN CON EL SOFTWARE O EL USO U OTROS TRATOS EN EL SOFTWARE.
