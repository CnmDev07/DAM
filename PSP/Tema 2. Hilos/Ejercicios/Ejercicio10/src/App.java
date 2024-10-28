/* Redactar un programa que lance dos hilos:
▪ El primero mostrará los números del 1 al 100, esperando un segundo (usando sleep) después de mostrar
cada número. Si recibiera una señal de interrupción, se comprobaría si el número es par, en cuyo caso el
hilo debe finalizar inmediatamente; si es impar, se continúa con el siguiente número
▪ El segundo mostrará los factores primos que componen un número. El constructor del objeto hilo recibirá
dos números: 'N' (el número a descomoner que puede ser un entero positivo muy grande) y 'nFactores'>0.
Si recibiera una señal de interrupción, se comprobaría si se han generado al menos 'nFactores', en cuyo
caso el hilo debe finalizar inmediatamente. En otro caso, se continua con la tarea.
Una vez lanzados los dos hilos, en main:
▪ se espera un tiempo aleatorio
▪ se interrumpe al primer hilo; deberá comprobarse a continuación si el hilo sigue 'vivo' y si el hilo ha
recibido una señal de interrupción. - vuelve a esperar otro tiempo aleatorio
▪ se interrumpe al segundo hilo, comprobando si el segundo hilo sigue vivo y si ha sido interrumpido. */

import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Numeros hiloNumeros = new Numeros();
        FactoresPrimos hiloPrimos = new FactoresPrimos(0, 0);
        Random r = new Random();
        
        hiloNumeros.start();
        hiloPrimos.start();

        try{

            Thread.sleep(r.nextInt(10000) + 1);
            hiloNumeros.interrupt();
            System.out.println((hiloNumeros.isAlive()) ? "Hilo numeros vivo" : "Hilo numeros muerto");
            System.out.println((hiloNumeros.isInterrupted()) ? "Hilo interrumpido" : "Hilo no interrumpido");
            

        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            
        }

        try{

            Thread.sleep(r.nextInt(3000) + 1000);
            hiloPrimos.interrupt();
            System.out.println((hiloPrimos.isAlive()) ? "Hilo primos vivo" : "Hilo primos muerto");
            System.out.println((hiloPrimos.isInterrupted()) ? "Hilo interrumpido" : "Hilo no interrumpido");

        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }   
}
