import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread {
    private long id;
    private String nombre;
    private String input;
    private Socket sc;
    private Protocolo protocolo;
    private Subasta subasta;

    public HiloCliente(long id, Socket sc, Subasta subasta) {
        this.id = id;
        this.nombre = "";
        this.input = "";
        this.sc = sc;
        this.protocolo = new Protocolo();
        this.subasta = subasta;
    }

    public HiloCliente() {
    }

    @Override
    public void run() {
        try (
                DataInputStream entrada = new DataInputStream(sc.getInputStream());
                DataOutputStream salida = new DataOutputStream(sc.getOutputStream());) {

            

        } catch (IOException e) {

        //} catch (InterruptedException e) {

        }
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Socket getSc() {
        return sc;
    }

    public void setSc(Socket sc) {
        this.sc = sc;
    }
}
