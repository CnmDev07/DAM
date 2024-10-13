import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Servidor  implements Runnable {

    private int puerto;
    private int timeout;
    private boolean stop;
    private ServerSocket servidor;
    private List<String> articulos;
    private int maxPujantes;

    public Servidor(){

        try {
            stop = false;
            Properties conf = new Properties();
            conf.load(new FileInputStream("src/servidor.properties"));
            puerto = Integer.parseInt(conf.getProperty("PUERTO"));
            timeout = Integer.parseInt(conf.getProperty("TIMEOUT"));
            articulos = Arrays.asList(conf.getProperty("ARTICULOS").split(","));
            maxPujantes = Integer.parseInt(conf.getProperty("MAXPUJANTES"));
        } catch (IOException e) {
            
        }

    }

    public static void main(String[] args) {
            Servidor s = new Servidor();
            Thread server = new Thread(s);
            server.start();
            stopServer(s);
        
    }

    @Override
    public void run() {
            startServer();
    }

    private void startServer() {
        while (!stop) {
            try (ServerSocket server = new ServerSocket(puerto);) {
                servidor = server;
                servidor.setSoTimeout(timeout);

                while (true) {
                    long numPujantes = 0;
                    int r = new Random().nextInt(2);
                    String nombre = articulos.get(r).split(",")[0];
                    int precioInicial = Integer.parseInt(articulos.get(r).split(",")[1]);
                    int precioFinalMinimo = Integer.parseInt(articulos.get(r).split(",")[1]);

                    Subasta subasta = new Subasta(nombre,precioInicial, precioFinalMinimo, maxPujantes);
                    while (numPujantes < maxPujantes) {
                        HiloCliente ta = new HiloCliente(numPujantes + 1, servidor.accept(), subasta);
                        numPujantes++;
                        subasta.setNumPujantes(numPujantes);
                        ta.start();
                    }
                }

            } catch (SocketTimeoutException e) {
                continue;
            } catch (IOException e) {
                
            }
        }
    }

    private static void stopServer(Servidor s) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pulsa s para terminar el servidor ");
        String entUsr;
        do {
            entUsr = sc.nextLine();
        } while (!entUsr.equalsIgnoreCase("s"));

        s.stop = true;
        sc.close();
    }
}