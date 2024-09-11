# Conway's Game of Life

Este proyecto implementa el famoso "Juego de la vida" de Conway en Java. El juego es una simulación de autómata celular donde las células en una cuadrícula viven, mueren o nacen según reglas específicas.

## Estructura del Proyecto

El proyecto está estructurado de la siguiente manera:

- `src/main/java/santiago/lab/ConwayGame.java`: Contiene la lógica principal del juego.
- `src/main/java/santiago/lab/Main.java`: Contiene el punto de entrada de la aplicación.

ConwayGame.java
Esta clase implementa el "Juego de la vida" de Conway, un autómata celular donde las células en una cuadrícula viven, mueren o nacen según reglas específicas.

### Métodos Principales

````java 
contarVecinasVivas(int x, int y):
````

Este método cuenta cuántas células vecinas están vivas alrededor de una célula en la posición (x, y).
Utiliza un bucle para revisar las 8 posiciones vecinas y aplica una operación de módulo para manejar los bordes del tablero de forma toroidal (es decir, el borde derecho se conecta con el izquierdo y el borde superior con el inferior).


````java
actualizarTablero():
````  
Este método actualiza el estado del tablero según las reglas del juego de Conway:

1.- Supervivencia: Una célula viva con 2 o 3 vecinas vivas sigue viva.

2.- Muerte por soledad: Una célula viva con menos de 2 vecinas vivas muere.

3.- Muerte por sobrepoblación: Una célula viva con más de 3 vecinas vivas muere.

4.- Nacimiento: Una célula muerta con exactamente 3 vecinas vivas nace.

Recorre cada célula del tablero, cuenta sus vecinas vivas y aplica las reglas para determinar el estado de la célula en el siguiente tablero.
Finalmente, copia el estado del siguiente tablero al tablero actual.
Este código permite simular el "Juego de la vida" de Conway en una cuadrícula de tamaño arbitrario, actualizando el estado de las células en cada iteración según las reglas del juego.