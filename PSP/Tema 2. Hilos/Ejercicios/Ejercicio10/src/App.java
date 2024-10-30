
import java.util.Random;

public class App {
    public static void main(String[] args) {
        
        Numeros hiloNumeros = new Numeros();
        FactoresPrimos hiloPrimos = new FactoresPrimos(1000000L, 5); // Ejemplo de N grande y nFactores

        
        hiloNumeros.start();
        hiloPrimos.start();

        try {
            
            Thread.sleep(new Random().nextInt(10000) + 1000);
            hiloNumeros.interrupt();
            System.out.println((hiloNumeros.isAlive()) ? "Hilo Numeros vivo" : "Hilo Numeros muerto");
            System.out.println((hiloNumeros.isInterrupted()) ? "Hilo Numeros interrumpido" : "Hilo Numeros no interrumpido");

            
            Thread.sleep(new Random().nextInt(3000) + 1000);
            hiloPrimos.interrupt();
            System.out.println((hiloPrimos.isAlive()) ? "Hilo Primos vivo" : "Hilo Primos muerto");
            System.out.println((hiloPrimos.isInterrupted()) ? "Hilo Primos interrumpido" : "Hilo Primos no interrumpido");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main fue interrumpido.");
        }
    }
}