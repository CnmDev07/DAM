import java.util.ArrayList;
import java.util.List;

class FactoresPrimos extends Thread {
    private final long numero;
    private final int nFactores;

    public FactoresPrimos(long numero, int nFactores) {
        this.numero = numero;
        this.nFactores = nFactores;
    }

    @Override
    public void run() {
        List<Long> factores = new ArrayList<>();
        long n = numero;
        
        for (long i = 2; i <= n; i++) {
            if (isInterrupted()) {
                if (factores.size() >= nFactores) {
                    System.out.println("Se han generado al menos " + nFactores + " factores, el hilo termina.");
                    return;
                } else {
                    System.out.println("No se han generado suficientes factores primos. Continúa el hilo.");
                }
            }

            while (n % i == 0) {
                factores.add(i);
                n /= i;
                System.out.println(i);

                if (factores.size() >= nFactores && isInterrupted()) {
                    System.out.println("Se han generado al menos " + nFactores + " factores, el hilo termina.");
                    return;
                }
            }
        }
        System.out.println("Descomposición completada.");
    }
}
