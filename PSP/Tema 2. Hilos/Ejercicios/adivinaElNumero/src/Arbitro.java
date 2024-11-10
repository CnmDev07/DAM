import java.util.Random;

public class Arbitro {
    
    private int numJugadores;
    private int turno;
    private int numeroAdivinar;
    private boolean terminado;

    public Arbitro(int numJugadores){

        this.numJugadores = numJugadores;
        this.turno = 1;
        this.numeroAdivinar = new Random().nextInt(10) + 1;
        this.terminado = false;

    }

    public synchronized int getTurno(){

        return turno;
    }

    public synchronized boolean isTerminado(){
        return terminado;
    }
    
    public synchronized void comprobarJugada(int jugadorID, int numeroJugado){

        if(!terminado){
            System.out.println("Jugador " + jugadorID + " juega el numero " + numeroJugado);

            if (numeroJugado == numeroAdivinar){
                System.out.println("¡Jugador " + jugadorID + " ha adivinado el numero!");
                terminado = true;
            }else{
                System.out.println("Jugador " + jugadorID + " fallo");
                
                if (turno > numJugadores){
                    turno = 1;
                }else{
                    turno = (turno % numJugadores) + 1;
                }
            }
        }

    }
}
