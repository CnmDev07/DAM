// Maximo.java
import java.util.Scanner;

public class Maximo {
    public static void main(String[] args) {
        int maximo = Integer.MIN_VALUE;

        // Leer desde la entrada estándar
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    int numero = Integer.parseInt(linea);
                    if (numero > maximo) {
                        maximo = numero;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(maximo);
    }
}
