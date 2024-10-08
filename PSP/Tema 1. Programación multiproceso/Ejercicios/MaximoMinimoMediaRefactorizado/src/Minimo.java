
import java.util.Scanner;

public class Minimo {
    public static void main(String[] args) {
        int minimo = Integer.MAX_VALUE;

        
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    int numero = Integer.parseInt(linea);
                    if (numero < minimo) {
                        minimo = numero;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(minimo);
    }
}
