/*Desarrolla un programa para simular un juego en el que varios jugadores intentan
adivinar un número. En este juego, se crean varios hilos, donde cada hilo representa
a un jugador. Habrá también un árbitro que se encarga de generar el número a
adivinar, comprobar las jugadas de los jugadores y determinar el turno de cada uno.
Requisitos del Juego:
• El número a adivinar debe estar comprendido entre 1 y 20.
• Se definirá un total de 3 clases: Árbitro, Jugador y Principal.
Clases:
1. Clase Árbitro
La clase Árbitro es responsable de generar el número a adivinar, llevar el control de
los turnos y mostrar el resultado final. Los atributos de esta clase incluyen:
• Número total de jugadores: Define la cantidad de jugadores que participan
en el juego.
• Turno: Indica el jugador cuyo turno está en curso.
• Número a adivinar: El número que debe ser adivinado por los jugadores.
• Juego terminado: Un valor booleano que indica si el juego ha finalizado.
Métodos:
• getTurno(): Devuelve el número del jugador cuyo turno es el actual.
• isJuegoTerminado(): Indica si el juego ha finalizado.
• comprobarJugada(int jugadorID, int numeroJugado): Este método verifica
la jugada de un jugador. Se define como synchronized para asegurar que el
acceso al recurso compartido (el estado del juego) sea seguro entre los
hilos. En este método:
o Se determina si el jugador ha acertado el número.
o Si un jugador adivina correctamente, el juego termina.
o Se indica el siguiente turno del jugador.
2. Clase Jugador
La clase Jugador extiende Thread (mejor implementa Runnable) y representa a
cada jugador en el juego. El constructor de esta clase recibe el identificador del 
jugador y el árbitro (el cual es compartido entre todos los jugadores). El
comportamiento del jugador es el siguiente:
• En el método run(), el jugador se asegura de que sea su turno. Si es su turno,
generará un número aleatorio entre 1 y 20.
• Luego, el jugador utiliza el método correspondiente del árbitro para hacer su
jugada.
• Este proceso de hacer jugadas se repetirá hasta que el juego se haya
acabado.
3. Clase Principal
La clase Principal es la encargada de inicializar el árbitro y de lanzar los hilos de los
jugadores. En esta clase:
• Se le pasa al árbitro el número de jugadores que participarán en el juego.
• Se crean los hilos para cada jugador, asignando un identificador único a
cada uno, y se les pasa el objeto árbitro para que lo compartan durante el
juego. */

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {
        int numJugadores;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el numero de jugadores");
        numJugadores = sc.nextInt();

        Arbitro arbitro = new Arbitro(numJugadores);
        ExecutorService es = Executors.newFixedThreadPool(numJugadores);

        for (int i = 1; i <= numJugadores; i++){
            es.execute(new Jugador(i, arbitro));
        }
        es.shutdown();
        
    }
}
