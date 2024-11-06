import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean terminado = false;
        int numero;
        int tiempoEspera = 10;
        String respuesta;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un numero entero positivo");
        numero = sc.nextInt();

        FactoresPrimos hilo = new FactoresPrimos(numero);
        hilo.start();

        try{

            while (!terminado){
                hilo.join(tiempoEspera);

                if(!hilo.isAlive()){
                    System.out.println("Los factores primos de tu numero son: " + hilo.getFactores());
                    terminado = true;
                }else{
                    System.out.println("¿Esperar mas? (s/n)");
                    respuesta = sc.next();

                    if (respuesta.equalsIgnoreCase("s")){
                        System.out.println("¿Cuanto mas?");
                        tiempoEspera = sc.nextInt();
                    }else{
                        hilo.interrupt();
                        terminado = true;
                    }
                }
            }

        }catch (InterruptedException e){
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
