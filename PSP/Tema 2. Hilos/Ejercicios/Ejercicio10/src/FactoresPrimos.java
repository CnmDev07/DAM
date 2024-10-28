import java.util.ArrayList;
import java.util.List;

public class FactoresPrimos extends Thread {
    
    private final long numero;
    private final int nFactores;

    public FactoresPrimos(long numero, int nFactores){

        this.numero = numero;
        this.nFactores = nFactores;

    }
    @Override  
    public void run(){

        List<Long> factores = new ArrayList<>();
        long n = numero;
        for(long i = 2; i <= n; i++ ){

            while(n % i == 0){

                factores.add(i);
                System.out.println(i);
                
            }
            if(isInterrupted()){

                if(factores.size() >= nFactores){

                    System.out.println("Se han generado al menos " + nFactores);
                    Thread.currentThread().interrupt();

                }else{

                    System.out.println("No se han generado suficientes factores primos. Continua el hilo");

                }

            }

        }

    }

}
