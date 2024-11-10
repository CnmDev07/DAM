import java.util.Random;
import java.util.Scanner;

public class Jugador implements Runnable {
    private int jugadorID;
    private Arbitro arbitro;
    private int numeroJugado;

    public Jugador(int jugadorID, Arbitro arbitro){
        this.jugadorID = jugadorID;
        this.arbitro = arbitro;
    }
    @Override
    public void run(){

        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        while(!arbitro.isTerminado()){

            synchronized (arbitro){

                if(arbitro.getTurno() == jugadorID){
                System.out.println("Jugador " + arbitro.getTurno() +  " introduce numero");
                numeroJugado = sc.nextInt();
                arbitro.comprobarJugada(jugadorID, numeroJugado);
                }

            }
            try {
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

    }
}
