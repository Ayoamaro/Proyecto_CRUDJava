# Proyecto Fútbol Java

Haz un proyecto en Java accediendo a la base de datos de dbFutbol.

El proyecto tendrá un menú principal en el cual elegiremos la base de datos con la cual trabajaremos (MySQL, SQL Server o Microsoft Access).

![](https://github.com/Ayoamaro/Proyecto_CRUDJava/blob/main/docs/images/menu_servers.PNG?raw=true)

La visualización deberás utilizar una presentación visual o de texto, y obligatoriamente utiliza las sentencias preparadas. Los datos de los servidores solo se escribirán una vez.

- Visualiza todos los datos de la tabla equipos visualizando también el nombre de la liga

  ![](https://github.com/Ayoamaro/Proyecto_CRUDJava/blob/main/docs/images/listado.PNG?raw=true)

- Inserta equipo. Haz un combo con las posibles ligas en presentación visual. Comprueba que los datos a introducir son correctos.

  ![](https://github.com/Ayoamaro/Proyecto_CRUDJava/blob/main/docs/images/insertar.PNG?raw=true)

- Elimina un equipo, si el equipo a eliminar tiene contratos preguntaremos si queremos eliminarlas todos (equipos y todos sus contratos) o no.

  ![](https://github.com/Ayoamaro/Proyecto_CRUDJava/blob/main/docs/images/borrado.PNG?raw=true)

- Modifica todos los datos de los equipos, el código del equipo no se podrá modificar. Deberás visualizar los datos que ya tiene.

- Utilizando los procedimientos y funciones almacenados (con los parámetros ya indicados) que tienes en la base de datos:

  - Insertar un equipo. Visualizar el mensaje correspondiente en función de los parámetros de salida. En el procedimiento decía devolver en un parámetro de salida:
    - Devolver en un parámetro de salida: 0 si la liga no existe y 1 si la liga existe.
    - Devolver en otro parámetro de salida: 0 si el equipo no se insertó y 1 si la inserción fue correcta.
  - Visualizar todos los contratos (Código de contrato, nombre de equipo, nombre de liga, fecha de inicio, fecha de fin, precio anual y precio de recisión) según un DNI o NIE del futbolista.
  - Visualizar la cantidad de futbolistas en activo (con contrato vigente) que hay en un equipo indicado, y cuantos futbolistas hay en activo de dicho equipo con precio anual y de recisión menor de los indicados.
  - Visualizar la cantidad total de meses que ha estado cierto futbolista (DNI o NIE) jugando en total.
  - - Devolver en un parámetro de salida: 0 si la liga no existe y 1 si la liga existe.
    - Devolver en otro parámetro de salida: 0 si el equipo no se insertó y 1 si la
      inserción fue correcta.

- En el acceso a Access no tendremos las opciones en las cuales accede a los procedimientos almacenados y funciones.
- Puedes añadir todas las opciones que consideres interesantes.
- Acuérdate de controlar todas las excepciones que se puedan producir.
