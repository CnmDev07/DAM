/*Redacta un programa denominado Sumador que reciba por la línea de comandos
una lista de números y vaya mostrando la suma acumulada de los mismos
paulatinamente por pantalla, haciendo una pausa después de mostrar cada
nueva suma. */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sumador {
    private static int suma = 0;
    public static void main(String[] args) throws Exception {
        Sumador s = new Sumador();
        if(args.length == 0){
            System.out.println("Introduce una serie de numeros");
        }else{

            ExecutorService es = Executors.newCachedThreadPool();

            for(String arg : args){

                es.execute(() -> {
                    try{
                        int numero = Integer.parseInt(arg);
                        suma += numero;
                        s.imprimirSuma(suma);
                        
                    }catch(NumberFormatException e){
                        e.printStackTrace();
                    }
                }
                    
                );
                
            }
            es.shutdown();

        }
    }
    private void imprimirSuma(int suma){
        System.out.println(suma);
        try{

            Thread.sleep(2000);

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
