import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class Tren1 {
    private int capacidadVagon;
    private Queue<Pasajero> vagon1;
    private Queue<Pasajero> vagon2;
    private boolean trenLleno = false;
    //private boolean viajeFinalizado = false;
    private int contViajes = 0;
    private int viajes;

    public Tren1(int capacidadVagon, int viajes) {
        this.capacidadVagon = capacidadVagon;
        vagon1 = new LinkedList<>();
        vagon2 = new LinkedList<>();
        this.viajes = viajes;
    }

    public int getViajes() {
        return viajes;
    }

    public synchronized void viajar(Pasajero p) throws InterruptedException {
        if (!vagon1.contains(p) && !vagon2.contains(p)) {
            if (contViajes == viajes) {
                System.out.println("No puede subir. El " + p.toString() + " espera");
                wait();
            }
            if (vagon1.size() < capacidadVagon) {
                vagon1.add(p);
                System.out.println("El " + p.toString() + " se sube al vagon 1");
                wait();
            } else if (vagon2.size() < capacidadVagon) {
                vagon2.add(p);
                if (vagon2.size() < capacidadVagon) {
                    System.out.println("El " + p.toString() + " se sube al vagon 2");
                    wait();
                } else {
                    System.out.println("El " + p.toString() + " se sube al vagon 2 y es el ultimo en entrar");
                    trenLleno = true;
                    System.out.println("Tren lleno");
                    notifyAll();
                    wait();
                }
            } else {
                System.out.println("No puede subir. El " + p.toString() + " espera");
                wait();
            }
        } else {
            wait();
        }

        /*if (viajeFinalizado) {
            if (vagon1.peek() == p) {
                vagon1.remove();
                System.out.println("Se baja el " + p.toString() + " del primer vagon");
            } else if (vagon1.isEmpty() && vagon2.peek() == p) {
                vagon2.remove();
                System.out.println("Se baja el " + p.toString() + " del segundo vagon");
                if (vagon2.isEmpty()) {
                    trenLleno = false;
                }
            }
        }*/
    }

    public synchronized void empezarViaje() throws InterruptedException {
        while (!trenLleno) {
            wait();
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.println("El Maquinista grita: 'il viaggio comincia' " + now.getMinute() + ":" + now.getSecond());
        notifyAll();
    }

    public synchronized void finalizarViaje() throws InterruptedException {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("El Maquinista grita: 'il viaggio finisce' " + now.getMinute() + ":" + now.getSecond());

        
        for (int i = 0; i < capacidadVagon; i++) {
          Pasajero p = vagon1.poll();
          System.out.println("Se baja el " + p.toString() + " del primer vagon");
        }
          
        for (int i = 0; i < capacidadVagon; i++) {
            Pasajero p = vagon2.poll();
            System.out.println("Se baja el " + p.toString() + " del segundo vagon");
        }
         
        //viajeFinalizado = true;
        //notifyAll();
        /*while (trenLleno) {
            wait();   
        }*/

        
        contViajes++;

        System.out.println("-------------------fin del viaje---------------------");
        notifyAll();
    }

    



}