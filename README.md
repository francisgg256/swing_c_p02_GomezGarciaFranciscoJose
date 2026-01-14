# Gestión de Apartamentos Turísticos Costa&Stay

Aplicación de escritorio desarrollada en **Java Swing** para gestionar apartamentos turísticos, permitiendo dar de alta nuevos pisos, calcular su precio mínimo por día y mostrar un resumen de la información del arrendador y del inmueble. 

## Contenido de la publicación

- `desarrollo/`  
  Contiene el proyecto completo de la aplicación (código fuente Java, formularios Swing y recursos gráficos como iconos y fotografías de los apartamentos).

- `ejecutable/`  
  Incluye el archivo ejecutable (`.jar`) de la aplicación, listo para ser ejecutado sin necesidad de abrir el IDE.

- `documentacion_tecnica/`  
  Carpeta generada con javadoc, donde se documentan las clases principales (`VentanaPrincipal`, `VentanaDialogo`, paneles de formulario, resumen, precio, etc.).

- `documentos/`  
  Documentos adicionales relacionados con la práctica, como el informe de usabilidad y accesibilidad de la interfaz de usuario.

## Desarrollo del proyecto

La aplicación se ha desarrollado en Java utilizando la librería **Swing** para la interfaz gráfica.  
Consta de una ventana principal desde la que se accede al alta de apartamentos y de un diálogo modal que agrupa los formularios de arrendador, inmueble, configuración de camas, datos de niños y extras, así como un resumen y el cálculo automático del precio mínimo por día. 

A nivel funcional, el programa valida los datos introducidos en los formularios, permite limpiar rápidamente el contenido para crear nuevos registros y muestra un panel de resumen con pestañas donde se visualiza la información del arrendador y del inmueble. 

## Despliegue

### Requisitos previos

- Java Runtime Environment (JRE) o JDK instalado (versión 8 o superior).
- Sistema operativo Windows (probado en aula; puede funcionar en otros sistemas con Java instalado).

### Ejecución en Windows (doble clic)

1. Descargar o clonar el repositorio.
2. Acceder a la carpeta `ejecutable/`. 
3. Hacer doble clic sobre el archivo `swing_c_p02_GomezGarciaFranciscoJose.jar`.

### Ejecución desde línea de comandos

1. Abrir una consola o terminal. 
2. Ir a la carpeta `ejecutable/` con el comando `cd`. 
3. Ejecutar:

   ```bash
   java -jar swing_c_p02_GomezGarciaFranciscoJose.jar

## Construido con

- **Lenguaje:** Java 24
- **Interfaz gráfica:** Swing
- **IDE:** Eclipse
- **Control de versiones:** Git y GitHub

## Versionado

Este proyecto utiliza Git para el control de versiones.  
La rama principal (`main`) contiene la versión estable correspondiente a la entrega de la práctica **SwingP02compendio**.

## Autores

- Francisco José Gómez García  
- 2º DAM – IES Al-Ándalus

## Licencia

Proyecto desarrollado con fines educativos dentro del módulo **Desarrollo de Interfaces** del ciclo DAM.  
