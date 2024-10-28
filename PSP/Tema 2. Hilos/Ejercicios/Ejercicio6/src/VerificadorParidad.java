/*Usando clases anónimas o expresiones lambda, redactar un programa
denominado VerificadorParidad al que se le pase por la línea de comandos una
secuencia de números enteros positivos y muestre por pantalla para cada uno si
es par o impar. El programa debe realizar la comprobación de paridad de
manera concurrente con todos los números */


import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VerificadorParidad {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        VerificadorParidad vp = new VerificadorParidad();

        if (args.length == 0){
            
            System.out.println("Introduce una secuencia de numeros");
            String entrada = sc.nextLine();
            String[] numeros =  entrada.split(" ");
            vp.verificarParidad(numeros);

        }else{

            vp.verificarParidad(args);

        }

    }

    private void verificarParidad(String args[]){

        ExecutorService es = Executors.newCachedThreadPool();

        for (String arg : args){

            try{
                int numero = Integer.parseInt(arg);

                Runnable tarea = () -> {
                    if(numero % 2 == 0){
                        System.out.println(numero + " es par");
                    }else{
                        System.out.println(numero + " es impar");
                    }
                };
                
                es.execute(tarea);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }

        }
        es.shutdown();

    }

}
