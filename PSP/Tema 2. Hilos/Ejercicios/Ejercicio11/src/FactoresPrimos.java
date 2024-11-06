import java.util.ArrayList;
import java.util.List;

public class FactoresPrimos extends Thread {
    
    private int numero;
    private final List<Integer> factores = new ArrayList<>();

    public FactoresPrimos(int numero){
        this.numero = numero;
    }
    
    public List<Integer> getFactores(){
        return factores;
    }

    @Override
    public void run(){
        int n = numero;
        for (int i = 2; i <= n && !Thread.currentThread().isInterrupted(); i++){
            while(n % i == 0){
                factores.add(i);
                n /= i;
            }
        }
    }
}
