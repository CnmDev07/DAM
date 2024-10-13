

public class BusquedaConcurrenteException extends Exception{
    String msg;

  public BusquedaConcurrenteException(String msg) {
    this.msg = msg
        + "\nUso correcto de la linea de comandos: -n numeroABuscar vX.txt [vX.txt]... [-n...]";
  }

  public void printMsg() {
    System.out.println(this.msg);
  }
}
