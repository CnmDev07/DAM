import java.time.LocalDateTime;
import java.util.LinkedList;

public class Tren {
    private int capacidadVagon;
    private LinkedList<Pasajero> vagon1;
    private LinkedList<Pasajero> vagon2;
    private boolean puedeSubir;
    private boolean viajando;
    private boolean fin;
    private boolean trenLleno;
    private int viajes;

    public Tren(int capacidadVagon, int viajes) {
        this.capacidadVagon = capacidadVagon;
        vagon1 = new LinkedList<>();
        vagon2 = new LinkedList<>();
        this.viajes = viajes;
        this.puedeSubir = true;
        this.viajando = false;
        this.fin = false;
        this.trenLleno = false;
    }

    public int getViajes() {
        return viajes;
    }

    public synchronized void viajar(Pasajero p) throws InterruptedException {
        while (!puedeSubir) {
            System.out.println("No puede subir. El " + p.toString() + " espera");
            wait();
        }
        if (vagon1.size() < capacidadVagon) {
            vagon1.add(p);
            System.out.println("El " + p + " se sube al vagon 1");
        } else if (vagon2.size() < capacidadVagon) {
            vagon2.add(p);
            System.out.print("El " + p + " se sube al vagon 2");
            if (vagon2.size() == capacidadVagon) {
                System.out.println(" y es el ultimo en entrar");
                viajando = true;
                puedeSubir = false;
                trenLleno = true;
                System.out.println("Tren lleno");
                notifyAll();
            } else {
                System.out.println();
            }
        }
        while (!viajando) {
            wait();
        }
        while (!fin) {
            wait();
        }

        bajar();
    }

    private void bajar() {
        if (!vagon1.isEmpty()) {
            System.out.println("Se baja el " + vagon1.poll() + " del primer vagon");
        } else if (!vagon2.isEmpty()) {
            System.out.println("Se baja el " + vagon2.poll() + " del segundo vagon");
            if (vagon2.isEmpty()) {
                trenLleno = false;
                notifyAll();
            }
        }
    }

    public synchronized void empezarViaje() throws InterruptedException {
        while (!viajando) {
            wait();
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.println("El Maquinista grita: 'il viaggio comincia' " + now.getMinute() + ":" + now.getSecond());
        notifyAll();
    }

    public synchronized void finalizarViaje() throws InterruptedException {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("El Maquinista grita: 'il viaggio finisce' " + now.getMinute() + ":" + now.getSecond());
        fin = true;
        viajando = false;
        viajes--;
        notifyAll();

        while (trenLleno) {
            wait();
        }

        System.out.println("-------------------fin del viaje---------------------");
        fin = false;
        if (getViajes() > 0)
            puedeSubir = true;
        else
            System.out.println("No quedan viajes");
        notifyAll();
    }
}