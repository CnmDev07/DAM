/*Vas a implementar un programa en Java que simule un mini servidor de procesamiento de solicitudes. Este
servidor estará gestionado por un hilo demonio que estará en ejecución continua, y atenderá solicitudes
de clientes.
▪Crea una clase Servidor que implemente Runnable. Esta clase debe simular un servidor que permanece a la
espera de solicitudes de procesamiento indefinidamente. El servidor debe:
▪ Imprimir un mensaje cada 3 segundos diciendo que está "Esperando nuevas solicitudes...“
▪ Deberá ser un hilo demonio, ya que el servidor solo debe estar activo mientras haya otras tareas principales en ejecución.
▪Crea una clase SolicitudCliente que implemente Runnable. Esta clase simulará una solicitud enviada por un
cliente para que el servidor la procese. El hilo debe:
▪ Imprimir un mensaje que diga "Solicitud enviada al servidor por [nombre del cliente]" al iniciarse.
▪ Simular el procesamiento de la solicitud con un retardo de 2 segundos, imprimiendo "Solicitud procesada por [nombre del cliente]"
al finalizar.
▪En la clase principal del programa: Crea un hilo Servidor y configúrala como un hilo demonio. Crea varias
instancias de hilos de SolicitudCliente (al menos 3) con nombres diferentes para simular clientes. Inicia primero
el hilo del servidor y luego las solicitudes de clientes de forma secuencial, dejando un pequeño retardo entre
cada una.
▪Observa el comportamiento del servidor. Al finalizar todas las solicitudes de los clientes, el hilo demonio del
servidor debe detenerse automáticamente. */

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
