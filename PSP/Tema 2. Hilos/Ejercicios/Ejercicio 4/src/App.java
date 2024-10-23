
/*4. Simulación de un Sistema de Monitoreo de Temperatura
Imagina que estás programando un sistema de monitoreo de temperatura para un invernadero. Este sistema
debe leer la temperatura cada cierto intervalo de tiempo y registrar si la temperatura está dentro del rango
óptimo (por ejemplo, entre 20°C y 30°C). Si la temperatura está fuera de este rango, se mostrará una
advertencia.
Instrucciones:
▪ Implementa una clase llamada TemperatureSensor que simule la lectura de la temperatura. Esta clase deberá
implementar la interfaz Runnable.
▪ El método run() de TemperatureSensor deberá simular la lectura de una temperatura aleatoria entre 15°C y
35°C (utiliza la clase Random para esto).
▪ Si la temperatura está dentro de rango muestra “La temperatura actual es: <temperatura>.
▪ Si la temperatura está fuera del rango de 20°C a 30°C, el sistema debe mostrar un mensaje de advertencia que incluya la
temperatura actual.
▪ Usa ScheduledExecutorService para programar la ejecución del sensor cada 3 segundos.
▪ Deja que el sistema funcione durante 30 segundos y luego apaga el servicio de manera ordenada. */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        TemperatureSensor ts = new TemperatureSensor();

        ses.scheduleWithFixedDelay(ts, 0, 3, TimeUnit.SECONDS); //(Runnable command, long initialDelay, long delay, TimeUnit unit)

        ses.schedule(new Runnable() {  //schedule(Runnable command, long delay, TimeUnit unit)
            @Override
            public void run(){
                ses.shutdown();
            }
        
        }, 30, TimeUnit.SECONDS);

        // ses.schedule(() -> { //Lambda con una interfaz que tiene un único método abstracto
        // ses.shutdown();
        // }, 30, TimeUnit.SECONDS); 
        
        

    }
}
